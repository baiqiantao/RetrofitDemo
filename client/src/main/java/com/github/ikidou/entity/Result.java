package com.github.ikidou.entity;

public class Result<T> {
	private int code;
	private String msg;
	private T data;
	private long count;
	private long page;
	
	@Override
	public String toString() {
		return "Result{" +
				"code=" + code +
				", msg='" + msg + '\'' +
				", data=" + data +
				", count=" + count +
				", page=" + page +
				'}';
	}
}
