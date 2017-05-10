package com.meiya.cunnar.elecsignner;
public class ResultException extends RuntimeException {

    private int errCode = 0;
    public static final int DEFAULT_ERROR_CODE = -11000;
    public static final String DEFALT_ERROR_MSG = "程序出现异常";

    public ResultException(int errCode, String msg) {
        super(msg);
        this.errCode = errCode;
    }
    public int getErrCode() {
        return errCode;
    }
}
