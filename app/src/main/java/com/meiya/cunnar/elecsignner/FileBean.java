package com.meiya.cunnar.elecsignner;

/**
 * Created by Administrator on 2017/5/5.
 */

public class FileBean {

    /**
     * success : true
     * data : {"file_id":81816}
     * response_time : 1493967280052
     * jsessionid : f3e314df-678b-4f14-96f3-f6106eb87402
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
         * file_id : 81816
         */

        private int file_id;

        public int getFile_id() {
            return file_id;
        }

        public void setFile_id(int file_id) {
            this.file_id = file_id;
        }
    }
}





