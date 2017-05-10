package com.meiya.cunnar.elecsignner;

import okhttp3.Interceptor;

import java.io.IOException;

/**
 * Created by Administrator on 2017/5/5.
 */

public class DownloadReponseInterceptor implements Interceptor {

    RetrofitRequestCallback<?> callback;
    DownloadReponseInterceptor(RetrofitRequestCallback<?> callback){
        this.callback = callback;
    }

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {

        okhttp3.Response response = chain.proceed(chain.request());
        //将ResponseBody转换成我们需要的FileResponseBody
        return response.newBuilder().body(new FileResponseBody(response.body(), callback)).build();
    }
}
