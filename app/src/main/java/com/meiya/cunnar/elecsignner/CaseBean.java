package com.meiya.cunnar.elecsignner;

/**
 * Created by Administrator on 2017/5/5.
 */

public class CaseBean {


    /**
     * success : true
     * data : {"cases_id":564}
     * response_time : 1493974550976
     * jsessionid : b777bd65-d604-44b8-bc9c-e19eebd9ea02
     */

    private boolean success;
    private DataBean data;
    private long response_time;
    private String jsessionid;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
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

    public static class DataBean {
        /**
         * cases_id : 564
         */

        private int cases_id;

        public int getCases_id() {
            return cases_id;
        }

        public void setCases_id(int cases_id) {
            this.cases_id = cases_id;
        }
    }
}
