package com.work.manager.util;

/**
 * 返回数据公共类
 * @author Jat
 *
 * @param <T> 返回详细数据实体对象
 */
public class Result<T> {
	
	private int status;
	private String msg;
	private T content;
	
	public Result(){
		this.setStatus(ResultStatus.SUCCESS);
	}
	
	public Result(ResultStatus status1){
		this.setStatus(status1);
	}
	
	public int getStatus() {
		return status;
	}

	public Result<T> setStatus(ResultStatus rs) {
		this.status = rs.getStatus();
		this.msg = rs.getMsg();
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public T getContent() {
		return content;
	}

	public Result<T> setContent(T content) {
		this.content = content;
        return this;
	}

	@Override
	public String toString() {
		return "ResJson [status=" + status + ", msg=" + msg + "]";
	}
	
}
