package com.meiya.cunnar.elecsignner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.gson.Gson;
import crazy_wrapper.Crazy.CrazyResponse;
import crazy_wrapper.Crazy.request.CrazyRequest;
import crazy_wrapper.Crazy.request.StringRequest;
import crazy_wrapper.Crazy.response.SessionResponse;
import crazy_wrapper.RequestManager;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends Activity implements SessionResponse.Listener<String>{

    public static final int LOGIN_CODE = 1111;
    String baseUrl = "xxxxxxxxxxxxxxxxxxxx";


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        final EditText user = (EditText) findViewById(R.id.username);
        final EditText pwd = (EditText) findViewById(R.id.password);
        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                startLogin(true,user.getText().toString(),pwd.getText().toString());
            }
        });

    }

    private void startLogin(boolean showDialog,String user,String pwd) {
        String url = baseUrl+"/login";
        StringBuilder sb = new StringBuilder(url);
        sb.append("?username=").append(user).append("&").append("password=").append(pwd);

        Map<String, String> header = new HashMap<>();
        header.put("Cookie", "sid=" + GuardPreference.getinstance(this).getToken());
        header.put("user-agent", "android/" + MYUtils.getVersionName(this));
        CrazyRequest crazyRequest = new StringRequest.Builder(this)
            .seqnumber(LOGIN_CODE)
            .url(sb.toString())
            .placeholderText("正在登录...")
            .headers(header)
            .loadMethod(showDialog?CrazyRequest.LOAD_METHOD.LOADING.ordinal() : CrazyRequest.LOAD_METHOD.NONE.ordinal())
            .execMethod(CrazyRequest.ExecuteMethod.FORM.ordinal())
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
        String results = response.result;
        int action = response.action;
        if (action == LOGIN_CODE){
            try {
                JSONObject jsonObject = new JSONObject(results);
                if (!jsonObject.isNull("success")) {
                    boolean success = jsonObject.getBoolean("success");
                    if (!success){
                        Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                        if (response.error != null && response.error.getCrazyResponse() != null){
                            CrazyResponse<String> crazyResponse = response.error.getCrazyResponse();
                            String resObj = crazyResponse.result;
                            Gson gson = new Gson();
                            ErrorResult er = gson.fromJson(resObj, ErrorResult.class);
                            Toast.makeText(LoginActivity.this, er != null ?er.getMsg():"登录失败", Toast.LENGTH_SHORT).show();

                        }
                        return;
                    }
                    Gson gson = new Gson();
                    LoginResult rr = gson.fromJson(results, LoginResult.class);
                    GuardPreference.getinstance(LoginActivity.this).setToken(rr.getJsessionid());
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
