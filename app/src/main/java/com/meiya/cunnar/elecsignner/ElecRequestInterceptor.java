package com.meiya.cunnar.elecsignner;

/**
 * Created by Administrator on 2017/5/4.
 */

import android.content.Context;
import okhttp3.Interceptor;
import okhttp3.Request;

import java.io.IOException;

/**
 * 请求拦截器，修改请求header
 */
public class ElecRequestInterceptor implements Interceptor {

    Context context;
    ElecRequestInterceptor(Context context){
        this.context = context;
    }
    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request request = chain.request()
            .newBuilder()
            .addHeader("Accept-Charset","utf-8")
            .addHeader("Accept","application/json")
            .addHeader("Content-Type","application/x-www-form-urlencoded")
            .addHeader("Connection","Keep-Alive")
            .addHeader("Accept-Language","utf-8")
            .addHeader("X-Requested-With","true")
            .addHeader("user-agent","android/" + MYUtils.getVersionName(context))
            .build();
        return chain.proceed(request);
    }
}
