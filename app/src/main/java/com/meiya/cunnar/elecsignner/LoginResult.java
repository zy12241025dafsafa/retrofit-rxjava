package com.meiya.cunnar.elecsignner;

/**
 * 登陆后返回的信息
 *
 * @author zhangy
 */
public class LoginResult {

    boolean success;
    AttachUserResult data;
    String jsessionid;
    long response_time;

    public long getResponse_time() {
        return response_time;
    }

    public void setResponse_time(long response_time) {
        this.response_time = response_time;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getJsessionid() {
        return jsessionid;
    }

    public void setJsessionid(String jsessionid) {
        this.jsessionid = jsessionid;
    }

    public AttachUserResult getData() {
        return data;
    }

    public void setData(AttachUserResult data) {
        this.data = data;
    }

    @Override public String toString() {
        return "LoginResult [success=" + success + ", data=" + data + ", jsessionid=" + jsessionid + "]";
    }



}
