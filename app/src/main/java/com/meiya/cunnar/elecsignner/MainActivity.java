package com.meiya.cunnar.elecsignner;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.google.gson.Gson;
import crazy_wrapper.Crazy.CrazyException;
import crazy_wrapper.Crazy.CrazyManager;
import crazy_wrapper.Crazy.CrazyResponse;
import crazy_wrapper.Crazy.Utils.Utils;
import crazy_wrapper.Crazy.dialog.ActionSheetDialog;
import crazy_wrapper.Crazy.dialog.OnOperItemClickL;
import crazy_wrapper.Crazy.network.FileHandler;
import crazy_wrapper.Crazy.request.CrazyRequest;
import crazy_wrapper.Crazy.request.FileRequest;
import crazy_wrapper.Crazy.request.StringRequest;
import crazy_wrapper.Crazy.response.SessionResponse;
import crazy_wrapper.MultiWrapRequests;
import crazy_wrapper.RequestManager;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import java.io.*;
import java.net.URI;
import java.util.*;

import static com.meiya.cunnar.elecsignner.MYUtils.*;

public class MainActivity extends Activity implements SessionResponse.Listener<String>{

    ListView listView;
    Button button;
    ListViewAdapter adapter;
    List<IllegalReportBean> alist;
    public static final int LIST_CODE = 102;
    public static final int COMMIT_REQUEST_WITH_STREAM = 103;
    public static final int STREAM_FILE_LENGTH_REQUEST = 104;
    public static final int STREAM_CREATE_FILE_REQUEST = 105;
    public static final int STREAM_UPLOAD_FILE_REQUEST = 106;
    public static final int CLUE_REQUEST = 107;
    public static final int REQUEST_SELECT_STREAM = 2;
    private StringBuilder streamPaths;
    StringBuilder fileKeys;//多个已上传的文件ID，正常需要存入数据库，方便断点续传时使用
    String baseUrl = "xxxxxxxxxxxxxxxx";

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listview);
        alist = new ArrayList<IllegalReportBean>();
        adapter = new ListViewAdapter(this,alist);
        listView.setAdapter(adapter);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                showSelectMediaDialog(MainActivity.this);
            }
        });
        fileKeys = new StringBuilder();
        streamPaths = new StringBuilder();
        //startRequestList(true);
        doRequestByRetrofitAndRxJava();
        //uploadFileWithRetrofit();

    }


    private void downloadFile(int fileID){
        CoreRetrofitCreater creater = CoreRetrofitCreater.getInstance().init(this,baseUrl);
        CoreRetrofitCreater.RetrofitRequestService mainService = creater.getService(new RetrofitRequestCallback<ResponseBody>() {

            @Override public void onFailure(Call<ResponseBody> call, Throwable t) {

            }

            @Override public void onSuccess(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override public void onLoading(long total, long progress) {
                super.onLoading(total, progress);
                MYUtils.LOG("a","下载进度 = "+(progress*100)/total);
            }
        });
        Map<String,String> params = new HashMap<String,String>();
        params.put("start_pos","0");
        params.put("file_id",String.valueOf(fileID));
        params.put("width","0");
        params.put("height","0");
        params.put("video_img","false");
        params.put("shuiyin","false");
        Observable<ResponseBody> downloadObservable = mainService.downloadFile("sid=" + GuardPreference.getinstance(this).getToken(), params);
        downloadObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<ResponseBody>() {

                @Override public void onCompleted() {
                    MYUtils.showToast(MainActivity.this,"下载完成");
                }

                @Override public void onError(Throwable e) {
                    String msg = getString(R.string.internal_request_error);
                    if (e != null && e instanceof ResultException) {
                        msg = ((ResultException)e).getMessage();
                    }
                    MYUtils.showToast(MainActivity.this,msg);
                }

                @Override public void onNext(ResponseBody result) {

                    try {
                        InputStream is = result.byteStream();
                        MYUtils.LOG("A","download = "+is.available()+"");
                        String filepath = Utils.createFilepath(Utils.DIR_CATEGORY.IMAGE, "download222.jpg");
                        File file = new File(filepath);
                        FileOutputStream fos = new FileOutputStream(file);
                        BufferedInputStream bis = new BufferedInputStream(is);
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = bis.read(buffer)) != -1) {
                            fos.write(buffer, 0, len);
                        }
                        fos.flush();
                        fos.close();
                        bis.close();
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
    }

    private Observable<IllegalReportResult> createListAction(String token, int pageSize, int pageNo) {
        CoreRetrofitCreater creater = CoreRetrofitCreater.getInstance().init(this,baseUrl);
        CoreRetrofitCreater.RetrofitRequestService mainService = creater.getService();
        Map<String,String> params = new HashMap<String,String>();
        params.put("page_size",String.valueOf(pageSize));
        params.put("page_no",String.valueOf(pageNo));
        return mainService.getClueList("sid="+token, params);
    }

    private Observable<FileBean> actionUpload(int fileID, int startPos,File file) {

        if (file == null || !file.exists()){
            return null;
        }
        // 添加描述
        String descriptionString = "hello, 这是文件描述";
        RequestBody description = RequestBody.create(
                MediaType.parse("multipart/form-data"), descriptionString);
        // 创建 RequestBody，用于封装构建RequestBody
        RequestBody requestFile =
            RequestBody.create(MediaType.parse("multipart/form-data"), file);

        //通过该行代码将RequestBody转换成特定的FileRequestBody
        FileRequestBody wrapperBody = new FileRequestBody(requestFile, new RetrofitRequestCallback() {

            @Override public void onSuccess(Call call, Response response) {
                MYUtils.LOG("a",response.body().toString());
            }

            @Override public void onFailure(Call call, Throwable t) {
                MYUtils.LOG("a",t.getMessage());
            }

            @Override public void onLoading(final long total, final long progress) {
                super.onLoading(total, progress);
                runOnUiThread(new Runnable() {
                    @Override public void run() {
                        MYUtils.showToast(MainActivity.this,"on loading progress = "+progress+" total = "+total);
                    }
                });
            }
        });

        // MultipartBody.Part  和后端约定好Key，这里的partName是用image
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), wrapperBody);
        String token = "sid="+GuardPreference.getinstance(this).getToken();
        CoreRetrofitCreater creater = CoreRetrofitCreater.getInstance().init(this,baseUrl);
        final CoreRetrofitCreater.RetrofitRequestService mainService = creater.getService();
        final Observable<FileBean> uploadObservable = mainService.uploadFile(token,file.length(),description, body,fileID,startPos);
        return uploadObservable;
    }

    private Observable<CaseBean> saveCaseMethod(String fid){
        CoreRetrofitCreater creater = CoreRetrofitCreater.getInstance().init(this,baseUrl);
        final CoreRetrofitCreater.RetrofitRequestService mainService = creater.getService();
        Map<String,String> cases = new HashMap<String,String>();
        cases.put("title", "crazy test");
        cases.put("description", "this is a demo request for crazy frame with stream");
        cases.put("file_ids", fid);
        Observable<CaseBean> caseObservable = mainService.saveCase("sid="+GuardPreference.getinstance(this).getToken(), cases);
        return caseObservable;
    }

    private void uploadFileWithRetrofit() {

        CoreRetrofitCreater creater = CoreRetrofitCreater.getInstance().init(this,baseUrl);
        final CoreRetrofitCreater.RetrofitRequestService mainService = creater.getService();

        String streamPath = Utils.createFilepath(Utils.DIR_CATEGORY.IMAGE, "test_4.jpg");
        final File file = new File(streamPath);

        Map<String,String> loginMap = new HashMap<String,String>();
        loginMap.put("username", "admin");
        loginMap.put("password", "admin");
        final Observable<LoginBean> login = mainService.login(loginMap);

        final Map<String,String> creatFile = new HashMap<String, String>();
        creatFile.put("file_content_type", "image/jpeg");
        creatFile.put("file_type", ".jpg");
        creatFile.put("time_length", "0");
        creatFile.put("file_name", "test_4.jpg");
        creatFile.put("file_size", String.valueOf(file.length()));


        login
        .flatMap(new Func1<LoginBean, Observable<FileBean>>() {
            @Override public Observable<FileBean> call(LoginBean loginBean) {
                GuardPreference.getinstance(MainActivity.this).setToken(loginBean.getJsessionid());
                return mainService.createFile(loginBean.getJsessionid(), creatFile);
            }
        })
        .flatMap(new Func1<FileBean, Observable<FileBean>>() {
            @Override public Observable<FileBean> call(FileBean fileBean) {
                return actionUpload(fileBean.getData().getFile_id(),-1,file);
            }
        })
        .flatMap(new Func1<FileBean, Observable<CaseBean>>() {
            @Override public Observable<CaseBean> call(FileBean fileBean) {
                return saveCaseMethod(String.valueOf(fileBean.getData().getFile_id()));
            }
        })

            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<CaseBean>() {

                @Override public void onCompleted() {
                    //MYUtils.showToast(MainActivity.this,"请求完成");
                }

                @Override public void onError(Throwable e) {
                    String msg = getString(R.string.internal_request_error);
                    if (e != null && e instanceof ResultException) {
                        msg = ((ResultException)e).getMessage();
                    }
                    MYUtils.showToast(MainActivity.this,msg);
                }

                @Override public void onNext(CaseBean result) {
                    try {
//                        int fileID = result.getData().getFile_id();
//                        MYUtils.showToast(MainActivity.this,fileID+"");
//                        downloadFile(fileID);
                        boolean a = result.isSuccess();
                    }catch (Exception e){

                    }
                }
            });


    }

    //先登录，再签到，再获取列表
    private void doRequestByRetrofitAndRxJava() {

        CoreRetrofitCreater creater = CoreRetrofitCreater.getInstance().init(this,baseUrl);
        final CoreRetrofitCreater.RetrofitRequestService mainService = creater.getService();
        Map<String,String> loginParams = new HashMap<String,String>();
        loginParams.put("username","admin");
        loginParams.put("password","admin");
        final Observable<LoginBean> login = mainService.login(loginParams);


        login
            .flatMap(new Func1<LoginBean, Observable<SignBean>>() {
                @Override public Observable<SignBean> call(LoginBean loginBean) {
                    return mainService.sign("sid="+loginBean.getJsessionid());
                }
            })
            .flatMap(new Func1<SignBean, Observable<IllegalReportResult>>() {
                @Override public Observable<IllegalReportResult> call(SignBean signBean) {
                    return createListAction(GuardPreference.getinstance(MainActivity.this).getToken(), 10, 1);
                }
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<IllegalReportResult>() {

                @Override public void onCompleted() {
                    //MYUtils.showToast(MainActivity.this,"请求完成");
                }

                @Override public void onError(Throwable e) {
                    String msg = getString(R.string.internal_request_error);
                    if (e != null && e instanceof ResultException) {
                        msg = ((ResultException)e).getMessage();
                    }
                    MYUtils.showToast(MainActivity.this,msg);
                }

                @Override public void onNext(IllegalReportResult illegalReportResult) {
                    MYUtils.showToast(MainActivity.this,illegalReportResult.getData().getTotalNum()+"");
                }
            });
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        CrazyManager.cancel();
    }

    public void showSelectMediaDialog(final Context context) {

        String[] arrays = getResources().getStringArray(R.array.array_illegal_report_upload_select_media);
        final ActionSheetDialog dialog = new ActionSheetDialog(context, arrays, null);
        dialog.isTitleShow(false).show();
        dialog.setOnOperItemClickL(new OnOperItemClickL() {
            @Override public void onOperItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialog.dismiss();
                switch (position) {
                    case 0:
                        selectPictureOrVideo((Activity) context);
                        break;
                }
            }
        });
    }

    public void selectPictureOrVideo(Activity activity) {
        Intent intent = new Intent();
        /* 开启Pictures画面Type设定为image */
        intent.setType("image/*,video/*");
        /* 使用Intent.ACTION_GET_CONTENT这个Action */
        intent.setAction(Intent.ACTION_PICK);
        activity.startActivityForResult(intent, REQUEST_SELECT_STREAM);
    }

    private String getSelectMediaPath(Uri uri) {
        if (uri == null)
            return null;
        String path = null;
        if (uri.toString().startsWith("content://")) {
            Cursor cursor = this.getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                }
                cursor.close();
            }
        } else {
            path = uri.getPath();
        }
        if (Utils.isEmptyString(path))
            return null;
        return path;
    }

    public static boolean checkType(String fileName, int which) {
        if (fileName != null) {
            List<String> list = null;
            if (which == IMAGE_TYPE_CASE) {
                list = Arrays.asList(SUFFIX_IMG.split(","));
            } else if (which == VIDEO_TYPE_CASE) {
                list = Arrays.asList(SUFFIX_VIDEO.split(","));
            } else if (which == AUDIO_TYPE_CASE) {
                list = Arrays.asList(SUFFIX_AUDIO.split(","));
            } else if (which == TEXT_TYPE_CASE) {
                list = Arrays.asList(SUFFIX_TEXT.split(","));
            }
            try {
                String[] split = fileName.split("\\.");
                String suffix = split[split.length - 1];
                for (int i = 0; i < list.size(); i++) {
                    if (suffix.equalsIgnoreCase(list.get(i))) {
                        return true;
                    }
                }
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case REQUEST_SELECT_STREAM:
                Uri uri = data.getData();
                String picturePathFromAblum = getSelectMediaPath(uri);
                if (checkType(picturePathFromAblum, IMAGE_TYPE_CASE)) {
                    String suffix = getFileSuffix(picturePathFromAblum);
                    String streamPath = Utils.createFilepath(Utils.DIR_CATEGORY.IMAGE, "test_" + System.currentTimeMillis() + suffix);
                    streamPaths.setLength(0);
                    streamPaths.append(streamPath).append(Utils.DEFAULT_SEPARATOR);
                    MYUtils.copyFile(new File(picturePathFromAblum), streamPath);
                } else if (checkType(picturePathFromAblum, VIDEO_TYPE_CASE)) {
                    String streamPath = saveVideoFile(data.getData());
                    streamPaths.append(streamPath).append(Utils.DEFAULT_SEPARATOR);
                }
                requestWithStream(true);
                break;
        }
    }

    private String saveVideoFile(Uri uri) {
        if (uri == null){
            return null;
        }
        String path = null;
        if (uri.toString().startsWith("content://")) {
            Cursor cursor = this.getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    path = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));
                }
                cursor.close();
            }
        } else {
            path = uri.getPath();
        }
        return path;
    }

    private String getFileSuffix(String filePath) {
        if (Utils.isEmptyString(filePath))
            return null;
        int index = filePath.lastIndexOf(".");
        if (index <= 0)
            return null;
        return filePath.substring(index);
    }

    /**
     * 带文件流请求时，将文件流封装成文件请求集
     * @param stream 多个文件流路径集合，以,分隔
     * @return
     */
    public List<CrazyRequest<?>> buildStreamRequests(String stream){

        List<CrazyRequest<?>> requests = new ArrayList<CrazyRequest<?>>();
        if (!Utils.isEmptyString(stream)){
            //DBUtils dbUtils = DBUtils.getInstance(context);
            List<String> paths = MYUtils.SplitString(stream, ",");
            for (String path : paths) {
                //若文件ID已经存在本地数据库，则请求获取已上传文件大小
                if (!Utils.isEmptyString(fileKeys.toString()) && Utils.isNumeric(fileKeys.toString())) {
                    String url = baseUrl+"/mass/s/get_temp_length";
                    StringBuilder sb = new StringBuilder(url).append("?file_id=").append(fileKeys.toString());
                    CrazyRequest<?> fileLengthRequest = new StringRequest.Builder(MainActivity.this)
                        .seqnumber(STREAM_FILE_LENGTH_REQUEST)
                        .path(path)//每个文件请求的文件流路径
                        .protocol(CrazyRequest.Protocol.HTTP.ordinal())
                        .priority(CrazyRequest.Priority.HIGH.ordinal())
                        .execMethod(CrazyRequest.ExecuteMethod.GET.ordinal())
                        .url(sb.toString())
                        .cascade(true)//下一个请求需等待此请求的结果，所以此处请求需要标识为级联请求
                        .create();
                    requests.add(fileLengthRequest);
                } else {
                    //创建文件记录
                    File file = new File(path);
                    if (!file.exists()) {
                        continue;
                    }
                    List<NameValuePair> list = new ArrayList<NameValuePair>();
                    list.add(new BasicNameValuePair("file_content_type", MYUtils.getMIMEType(path)));
                    list.add(new BasicNameValuePair("file_type", path.substring(path.lastIndexOf(".") + 1, path.length())));
                    list.add(new BasicNameValuePair("time_length", "0"));
                    list.add(new BasicNameValuePair("file_name", file.getName()));
                    list.add(new BasicNameValuePair("file_size", String.valueOf(file.length())));
                    String url = baseUrl+"/mass/s/create_file_item";
                    StringBuilder sb = new StringBuilder(url).append("?").append(URLEncodedUtils.format(list, "utf-8"));

                    CrazyRequest<?> createFileRequest = new StringRequest.Builder(MainActivity.this)
                        .seqnumber(STREAM_CREATE_FILE_REQUEST)
                        .path(path)//每个文件请求的文件流路径
                        .protocol(CrazyRequest.Protocol.HTTP.ordinal())
                        .priority(CrazyRequest.Priority.HIGH.ordinal())
                        .execMethod(CrazyRequest.ExecuteMethod.FORM.ordinal())
                        .url(sb.toString())
                        .cascade(true)//下一个请求需等待此请求的结果，所以此处请求需要标识为级联请求
                        .create();
                    requests.add(createFileRequest);
                }
                //上传文件流
                long startPos = 0;
                ArrayList<NameValuePair> list2 = new ArrayList<NameValuePair>();
                //先使用文件路径替代文件ID做占位
                list2.add(new BasicNameValuePair("file_id", path));
                list2.add(new BasicNameValuePair("start_pos", String.valueOf(startPos > 0 ? startPos : -1)));
                String url = baseUrl+"/mass/s/upload_file_item";
                StringBuilder sb = new StringBuilder(url).append("?").append(URLEncodedUtils.format(list2, "utf-8"));

                CrazyRequest<?> uploadFileRequest = new FileRequest.Builder(MainActivity.this)
                    .seqnumber(STREAM_UPLOAD_FILE_REQUEST)
                    .path(path)//每个文件请求的文件流路径
                    .protocol(CrazyRequest.Protocol.UPLOAD.ordinal())
                    .priority(CrazyRequest.Priority.HIGH.ordinal())
                    .execMethod(CrazyRequest.ExecuteMethod.FORM.ordinal())
                    .url(sb.toString())
                    .cascade(true)//下一个请求需等待此请求的结果，所以此处请求需要标识为级联请求
                    .deleteStreamAfterRequestSuccess(false)//上传成功后，是否删除上传记录
                    .deleteTempFile(false)//上传成功后，是否删除上传的文件
                    .create();
                requests.add(uploadFileRequest);
            }
            return requests;
        }
        return requests;
    }

    /**
     * 提交数据中的url,zhangy add 20161017
     */
    private String testCommit(String streams){

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("title", "crazy test"));
        params.add(new BasicNameValuePair("description", "this is a demo request for crazy frame with stream"));
        params.add(new BasicNameValuePair("file_ids", streams));

        String url = baseUrl+"/save_cases_item";
        StringBuilder sb = new StringBuilder(url).append("?").append(URLEncodedUtils.format(params, "utf-8"));
        return sb.toString();
    }

    private void requestWithStream(boolean showDialog){


        String streams = streamPaths.toString();
        if (streams.endsWith(Utils.DEFAULT_SEPARATOR)) {
            streams = streamPaths.deleteCharAt(streamPaths.length()-1).toString();
        }
        String url = testCommit(streams);
        List<CrazyRequest<?>> requests = new ArrayList<>();
        requests.addAll(buildStreamRequests(streams));
        CrazyRequest clueRequest = new StringRequest.Builder(this)
            .seqnumber(CLUE_REQUEST)
            .url(url)
            .execMethod(CrazyRequest.ExecuteMethod.FORM.ordinal())
            .placeholderText("正在获取...")
            //.loadMethod(CrazyRequest.LOAD_METHOD.NONE.ordinal())组合请求时，以组合包装请求的前台加载方式为准
            .create();
        requests.add(clueRequest);

        String tag = UUID.randomUUID().toString();
        int loadMethod = showDialog? CrazyRequest.LOAD_METHOD.PROGRESS.ordinal(): CrazyRequest.LOAD_METHOD.NONE.ordinal();
        SessionResponse.Listener<?> listener = new CompoundRequestWithStreamListener();
        int seqnumber = CLUE_REQUEST;
        String placeText = "正在提交...";

        Map<String, String> header = new HashMap<>();
        header.put("Cookie", "sid=" + GuardPreference.getinstance(this).getToken());
        header.put("user-agent", "android/" + MYUtils.getVersionName(this));
        header.put("X-Requested-With", "true");


        CrazyRequest<?> multiRequests = new MultiWrapRequests(requests);
        multiRequests.setListener(listener);
        multiRequests.setSeqnumber(seqnumber);
        multiRequests.setTag(tag);
        multiRequests.setStreams(streams);
        multiRequests.setLoadMethod(loadMethod);
        multiRequests.setGroupRequest(true);//是否组合请求
        multiRequests.setPlaceholdText(placeText);
        multiRequests.setHeaders(header);

        RequestManager.getInstance().startRequest(this,multiRequests);

    }

    /**
     * 当组合请求且包含文件流请求时，定义一个crazy listener，处理文件流进度与各子请求的结果交换
     * 组合请求中下一个子请求需要等到上一个子请求结果后更新请求数据，此时上一个请求需要设置为级联请求cascade
     */
    private final class CompoundRequestWithStreamListener implements SessionResponse.RequestChangeListener<String>,
        FileHandler.FileHandlerListener{

        @Override public void onHandleStatus(CrazyRequest<?> request, long currentSize, long totalSize, boolean notError) {
            Log.e("","totalsize = " + totalSize + "creentsize = " + currentSize);
        }

        @Override public long sizeOf(String path) {
            return 0;
        }

        @Override public void onResponse(SessionResponse<String> response) {
            Log.e("","response = " + response);
            if (response == null) {
                Toast.makeText(MainActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                RequestManager.getInstance().afterRequest(response);
                return;
            }
            if (response != null && response.action == CLUE_REQUEST) {
                RequestManager.getInstance().afterRequest(response);
                if (!response.isSuccess()) {
                    if (response.error != null && response.error.getCrazyResponse() != null){
                        CrazyResponse<String> crazyResponse = response.error.getCrazyResponse();
                        String resObj = crazyResponse.result;
                        Gson gson = new Gson();
                        ErrorResult er = gson.fromJson(resObj, ErrorResult.class);
                        Toast.makeText(MainActivity.this, er != null ?er.getMsg():"请求失败", Toast.LENGTH_SHORT).show();

                    }
                    return;
                }
                Toast.makeText(MainActivity.this,"带文件流请求成功",Toast.LENGTH_LONG).show();
            }
        }

        @Override public CrazyRequest<?> requestChange(CrazyRequest<?> request, SessionResponse<String> response) throws CrazyException {
            return handlerChangeRequest(request,response);
        }

        @Override public CrazyRequest<?> beforeRequest(CrazyRequest<?> request) throws CrazyException {
            return request;
        }

        @Override public void afterRequest(CrazyRequest<?> request, SessionResponse<?> response) throws CrazyException {
            if (response != null && response.isSuccess() && response.action == STREAM_CREATE_FILE_REQUEST) {
                try {
                    JSONObject jo = new JSONObject((String) response.result);
                    if(jo.isNull("data")){
                        return;
                    }
                    JSONObject fileidResult = jo.getJSONObject("data");
                    if (fileidResult.isNull("file_id")) {
                        return;
                    }
                    String fileKey = fileidResult.getString("file_id");
                    fileKeys.append(fileKey).append(Utils.DEFAULT_SEPARATOR);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *
     * @param request 下一个请求对象
     * @param response 上一个请求结果
     */
    private CrazyRequest<?> handlerChangeRequest(CrazyRequest<?> request, SessionResponse<String> response) {
        if (response == null){
            return request;
        }
        if (!response.isSuccess()){
            return request;
        }
        try {
            String result = response.result;
            if (Utils.isEmptyString(result)) {
                return request;
            }
            if (Utils.isEmptyString(request.getUrl())) {
                return request;
            }
            //若是取文件已上传大小后再进行文件流上传的交换操作，则解析已上传文件长度的结果
            if (request.getSeqnumber() == STREAM_UPLOAD_FILE_REQUEST && response.action == STREAM_FILE_LENGTH_REQUEST) {
                if (Utils.isEmptyString(request.getPath())) {
                    return request;
                }
                File file = new File(request.getPath());
                if (!file.exists()) {
                    return request;
                }
                List<NameValuePair> params = URLEncodedUtils.parse(URI.create(request.getUrl()), "utf-8");
                if (params == null) {
                    return request;
                }
                //从上传表中取出rowKey
                String rowKey = "";/*DBUtils.getInstance(context).getFileRk(context, request.getPath());*/
                List<NameValuePair> newParams = new ArrayList<NameValuePair>();
                newParams.add(new BasicNameValuePair("file_id", rowKey));

                JSONObject json = null;
                json = new JSONObject(result);
                if (!json.isNull("data")) {
                    JSONObject temp = json.getJSONObject("data");
                    if (!temp.isNull("temp_length")) {
                        long uploadedLength = temp.getLong("temp_length");
                        newParams.add(new BasicNameValuePair("start_pos", String.valueOf(uploadedLength > 0 ? uploadedLength : -1)));
                        request.setLastPushPos(uploadedLength);
                    }
                }
                String url = getBaseUrl(request.getUrl()) + "?" + URLEncodedUtils.format(newParams, "utf-8");
                request.setUrl(url);
                return request;
                //若上一个请求是上传文件，下一个请求是提交clue记录，则更新下一个请求中的
            } else if (response.action == STREAM_UPLOAD_FILE_REQUEST && request.getSeqnumber() == CLUE_REQUEST) {
                String url = request.getUrl();
                List<NameValuePair> params = URLEncodedUtils.parse(new URI(url), Utils.CHAR_FORMAT);
                int pos = 0;
                if (params != null && !params.isEmpty()) {
                    for (int i=0;i<params.size();i++) {
                        if (params.get(i).getName().equalsIgnoreCase("file_ids")){
                            pos = i;
                            break;
                        }
                    }
                }
                String fileKeyString = fileKeys.toString();
                if (fileKeyString.endsWith(Utils.DEFAULT_SEPARATOR)) {
                    fileKeyString = fileKeys.deleteCharAt(fileKeys.length()-1).toString();
                }
                NameValuePair pair = new BasicNameValuePair("file_ids", fileKeyString);
                params.set(pos, pair);
                url = getBaseUrl(url) + "?" + URLEncodedUtils.format(params, Utils.CHAR_FORMAT);
                request.setUrl(url);
                return request;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return request;
    }

    private String getBaseUrl(String url){
        if (Utils.isEmptyString(url)){
            return null;
        }
        String baseUrl = url.substring(0,url.lastIndexOf("?"));
        return baseUrl;
    }

    private void startRequestList(boolean showDialog) {

        Map<String, String> header = new HashMap<>();
        header.put("Cookie", "sid=" + GuardPreference.getinstance(this).getToken());
        header.put("user-agent", "android/" + MYUtils.getVersionName(this));
        String url = baseUrl+"/mass/s/get_clue_list";
        StringBuilder sb = new StringBuilder(url);
        sb.append("?page_size=10").append("&").append("page_no=1");
        CrazyRequest crazyRequest = new StringRequest.Builder(this)
            .seqnumber(LIST_CODE)
            .url(sb.toString())
            .headers(header)
            .placeholderText("正在获取...")
            .loadMethod(showDialog?CrazyRequest.LOAD_METHOD.LOADING.ordinal() : CrazyRequest.LOAD_METHOD.NONE.ordinal())
            .shouldCache(true)
            .refreshAfterCacheHit(true)
            .create();
        RequestManager.getInstance().startRequest(this,this,crazyRequest);
    }

    @Override public void onResponse(SessionResponse<String> response) {
        if (isFinishing()) {
            return;
        }
        RequestManager.getInstance().afterRequest(response);
        if (response == null) {
            return;
        }
        try {
            if (!response.isSuccess()) {
                if (response.error != null && response.error.getCrazyResponse() != null){
                    CrazyResponse<String> crazyResponse = response.error.getCrazyResponse();
                    String resObj = crazyResponse.result;
                    Gson gson = new Gson();
                    ErrorResult er = gson.fromJson(resObj, ErrorResult.class);
                    Toast.makeText(this, er != null ?er.getMsg():"获取失败", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            IllegalReportResult gsonResult = MYUtils.genToBean(response.result, IllegalReportResult.class);
            if (gsonResult != null) {
                if (gsonResult.getData() != null){
                    List<IllegalReportBean> list = gsonResult.getData().getResults();
                    if (list != null) {
                        alist.clear();
                        alist.addAll(list);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this,"获取失败", Toast.LENGTH_SHORT).show();
        }

    }

    private final class ListViewAdapter extends BaseAdapter{

        Context context;
        List<IllegalReportBean> list;
        LayoutInflater mInflater;
        ListViewAdapter(Context context, List<IllegalReportBean> list) {
            this.context = context;
            this.list = list;
            mInflater = LayoutInflater.from(context);
        }

        @Override public int getCount() {
            return list != null ? list.size() : 0;
        }

        @Override public Object getItem(int position) {
            return list.get(position);
        }

        @Override public long getItemId(int position) {
            return position;
        }

        @Override public View getView(int position, View convertView, ViewGroup parent) {
            IllegalReportBean illegalReportBean = list.get(position);
            ViewHolder viewHolder;
            if(convertView == null){
                viewHolder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.listitem, null);
                viewHolder.title = (TextView)convertView.findViewById(R.id.text);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder)convertView.getTag();
            }
            viewHolder.title.setText(illegalReportBean.getClueTypeName());
            return convertView;
        }
    }

    static class ViewHolder
    {
        public TextView title;
    }


}
