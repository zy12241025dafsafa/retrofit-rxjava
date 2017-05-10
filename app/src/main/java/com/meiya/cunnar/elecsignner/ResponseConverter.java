package com.meiya.cunnar.elecsignner;

import android.util.Log;
import com.google.gson.Gson;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Converter;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by zhangy on 2017/5/4.
 * 业务内数据约定解析转换器
 */

class ResponseConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;

    public ResponseConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        Log.d("Network", "response>>" + response);
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.isNull("success")){
                throw new ResultException(ResultException.DEFAULT_ERROR_CODE,"请求失败");
            }
            if (jsonObject.getBoolean("success")) {
                return gson.fromJson(response, type);
            }else{
                ErrorResult errResponse = gson.fromJson(response, ErrorResult.class);
                throw new ResultException(errResponse.getCode(), errResponse.getMsg());
            }
        } catch (JSONException e) {
            e.printStackTrace();
            throw new ResultException(ResultException.DEFAULT_ERROR_CODE,ResultException.DEFALT_ERROR_MSG);
        }
    }
}
