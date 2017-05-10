package com.meiya.cunnar.elecsignner;

import com.google.gson.Gson;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * Created by zhangy elec convert factory on 2017/5/4.
 */

public class ElecConverterFactory extends Converter.Factory {
    Gson gson;
    public static ElecConverterFactory create() {
        return create(new Gson());
    }
    /**
     * Create an instance using {@code gson} for conversion. Encoding to JSON and
     * decoding from JSON (when no charset is specified by a header) will use UTF-8.
     */
    public static ElecConverterFactory create(Gson gson) {
        return new ElecConverterFactory(gson);
    }

    ElecConverterFactory(Gson gson) {
        this.gson = gson;
    }

    @Override public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new ResponseConverter<>(gson,type);
    }

}
