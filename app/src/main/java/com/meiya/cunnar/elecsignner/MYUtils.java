package com.meiya.cunnar.elecsignner;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;
import com.google.gson.Gson;
import crazy_wrapper.Crazy.Utils.CustomToast;
import crazy_wrapper.Crazy.Utils.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/28.
 */

public class MYUtils {

    public final static String SUFFIX_IMG = "jpg,jpeg,png,gif,bmp";
    public final static String SUFFIX_VIDEO = "3gp,avi,rmvb,rm,flm,mp4,wmv,flv,prfm,mpeg,flm,mpg,ra,mov,qt,asf,navi,mkv,f4v," + "ram,webm,m4v";
    public final static String SUFFIX_AUDIO = "mp3,wav,amr,wma,prfa,midi,aif,m4a,xmf,ogg,mid,3gpp";
    public final static String SUFFIX_TEXT = "doc,docx,xls,xlsx,ppt,pptx,pdf,txt,xml,eml";

    public static final int IMAGE_TYPE_CASE = 0;
    public static final int VIDEO_TYPE_CASE = 1;
    public static final int AUDIO_TYPE_CASE = 2;
    public static final int TEXT_TYPE_CASE = 3;
    public static final int OTHER_TYPE_CASE = 4;

    public static final boolean DEBUG = true;

    /**
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        String strVersionName = null;

        try {
            String packageName = context.getPackageName();
            PackageInfo info = context.getPackageManager().getPackageInfo(packageName, 0);
            if (null != info) {
                strVersionName = info.versionName;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return strVersionName;
    }

    public static void LOG(String TAG, String txt) {
        if (DEBUG) {
            Log.d(TAG, txt);
        }
    }

    public static <T> T genToBean(String json, Class c) {
        if (Utils.isEmptyString(json))
            return null;
        Gson gson = new Gson();
        return (T) gson.fromJson(json, c);
    }

    public static ArrayList<String> SplitString(String s, String format) {
        ArrayList<String> innerUrl = new ArrayList<String>();
        if (Utils.isEmptyString(s)){
            return innerUrl;
        }
        String[] ss;
        ss = s.split(format);
        for (int i = 0; i < ss.length; i++) {
            innerUrl.add(ss[i]);
        }
        return innerUrl;
    }

    public static String getMIMEType(String filePath) {
        URL url;
        try {
            filePath = "file://" + filePath;
            url = new URL(filePath);
            URLConnection ul = url.openConnection();
            String mime = ul.getContentType();
            return mime;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void copyFile(File oldfile, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            //File oldfile = new File(oldPath);
            if (oldfile.exists()) {
                InputStream inStream = new FileInputStream(oldfile);
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread;
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        } catch (Exception e) {
            System.out.println("error  ");
            e.printStackTrace();
        }
    }

    public static void showToast(Context context,String showText,int gravity) {
        if (Utils.isEmptyString(showText)) {
            return;
        }
        CustomToast.showToast(context, showText, Toast.LENGTH_LONG,gravity);
    }

    public static void showToast(Context context,String showText) {
        showToast(context,showText, Gravity.CENTER);
    }

    public static void showToast(Context context,int sid) {
        CustomToast.showToast(context, sid, Toast.LENGTH_LONG);
    }
}
