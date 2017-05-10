package com.meiya.cunnar.elecsignner;

/**
 * Created by Administrator on 2017/5/4.
 */

public class SignBean {

    /**
     * success : true
     * data : 签到成功，已领取1积分
     * response_time : 1493888963506
     * jsessionid : 1e061b85-402e-485a-84aa-579ce09b3087
     */

    private boolean success;
    private String data;
    private long response_time;
    private String jsessionid;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getResponse_time() {
        return response_time;
    }

    public void setResponse_time(long response_time) {
        this.response_time = response_time;
    }

    public String getJsessionid() {
        return jsessionid;
    }

    public void setJsessionid(String jsessionid) {
        this.jsessionid = jsessionid;
    }
}
