package com.meiya.cunnar.elecsignner;

public class ErrorResult {

	int code;
	String msg;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "errorResponseResult [code=" + code + ", msg=" + msg + "]";
	}
	
	
	
}
