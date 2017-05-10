package com.meiya.cunnar.elecsignner;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.http.*;
import rx.Observable;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/5/4.
 */

public class CoreRetrofitCreater {

    static CoreRetrofitCreater retrofitCreater;
    public static final int DEFAULT_TIMEOUT = 30;
    String domainUrl;
    Context context;
    Retrofit retrofit;
    RetrofitRequestService service;
    RetrofitRequestCallback<?> callback;

    public static CoreRetrofitCreater getInstance() {
        if (retrofitCreater == null) {
            retrofitCreater = new CoreRetrofitCreater();
        }
        return retrofitCreater;
    }

    public <T> Retrofit create(RetrofitRequestCallback<T> callback) throws IllegalStateException{
        if (domainUrl.equalsIgnoreCase("")){
            throw new IllegalStateException("domain url is empty");
        }
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
            .addConverterFactory(ElecConverterFactory.create(gson))
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .baseUrl(this.domainUrl)
            .client(getOkHttpClient(callback))
            .build();
        return retrofit;
    }

    public CoreRetrofitCreater init(Context context,String domainUrl) {
        this.domainUrl = domainUrl;
        this.context = context;
        return this;
    }

    private <T> OkHttpClient getOkHttpClient(RetrofitRequestCallback<T> callback) {
        //定制OkHttp
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient
            .Builder();
        //设置超时时间
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        //使用拦截器
        httpClientBuilder.addInterceptor(new ElecRequestInterceptor(context));
        if(BuildConfig.DEBUG){
            //log拦截器
            //HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor();
            //loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClientBuilder.addInterceptor(new LogInterceptor());
        }
        //设置下载文件时的 response 拦截
        httpClientBuilder.addInterceptor(new DownloadReponseInterceptor(callback));
        return httpClientBuilder.build();
    }

    //所有请求的service接口
    public interface RetrofitRequestService{

        @POST("/mass/s/login")
        @FormUrlEncoded
        //Observable<LoginBean> login(@Query("username") String username,@Query("password") String password);
        Observable<LoginBean> login(@FieldMap Map<String,String> fields);

        @GET("/mass/s/get_clue_list")
//        Observable<IllegalReportResult> getClueList(@Header("Cookie") String cookies,@Query("page_size") int pageSize,
//            @Query("page_no") int pageNo);
        Observable<IllegalReportResult> getClueList(@Header("Cookie")String cookie, @QueryMap Map<String,String> querys);

        @POST("/mass/s/sign")
        Observable<SignBean> sign(@Header("Cookie") String cookies);

        @POST("/mass/s/create_file_item")
        @FormUrlEncoded
        Observable<FileBean> createFile(@Header("Cookie") String cookie,@FieldMap Map<String, String> params);

        @Multipart
        @POST("/mass/s/upload_file_item")
        Observable<FileBean> uploadFile(@Header("Cookie") String cookie,@Header("Content-Length")long length,
            @Part("description") RequestBody description,
            @Part MultipartBody.Part file,@Query("file_id") int file_id,@Query("start_pos") int pos);

        @GET("/mass/s/download_file_item")
        Observable<ResponseBody> downloadFile(@Header("Cookie")String cookie, @QueryMap Map<String,String> params);

        @POST("/mass/s/save_cases_item")
        @FormUrlEncoded
        Observable<CaseBean> saveCase(@Header("Cookie") String cookies,@FieldMap Map<String,String> params);


    }

    public RetrofitRequestService getService() {
        return getService(null);
    }

    public <T> RetrofitRequestService getService(RetrofitRequestCallback<T> callback) {
        this.callback = callback;
        if (retrofit == null) {
            retrofit = create(this.callback);
        }
        if (service == null) {
            service = retrofit.create(RetrofitRequestService.class);
        }
        return service;
    }
}
