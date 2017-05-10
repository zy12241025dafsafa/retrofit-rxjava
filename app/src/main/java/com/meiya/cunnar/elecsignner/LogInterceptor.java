package com.meiya.cunnar.elecsignner;

import android.util.Log;
import okhttp3.Interceptor;
import okhttp3.Request;

import java.io.IOException;
import java.util.Locale;

/**
 * retrofit 请求日志拦截器
 */
class LogInterceptor implements Interceptor {
    private String TAG = "okhttp";

    @Override
    public okhttp3.Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        MYUtils.LOG(TAG, "request:" + request.toString());
        long t1 = System.nanoTime();
        okhttp3.Response response = chain.proceed(chain.request());
        long t2 = System.nanoTime();
        Log.v(TAG, String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s",
            response.request().url(), (t2 - t1) / 1e6d, response.headers()));
        okhttp3.MediaType mediaType = response.body().contentType();
        String content = response.body().string();
        Log.i(TAG, "response body:" + content);
        return response.newBuilder()
            .body(okhttp3.ResponseBody.create(mediaType, content))
            .build();
    }
}
