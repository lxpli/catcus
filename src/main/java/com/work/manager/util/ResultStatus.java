package com.work.manager.util;

/**
 * 返回状态码和描述的枚举
 * @author Jat
 *
 */
public enum ResultStatus {
	
	SUCCESS(0, "success"),
	
	FAILED(10001,"操作错误");
	
	private int status;
	private String msg;

	private ResultStatus(int status, String msg){
		this.status = status;
		this.msg = msg;
	}

	public int getStatus(){
		return this.status;
	}

	public String getMsg() {
		return this.msg;
	}
	
}
