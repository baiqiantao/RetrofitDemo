package com.github.ikidou;

public class Resp {
	private int code;
	private String msg;
	private Object data;
	public long count;
	public long page;
	
	public static Resp create(int code, String msg) {
		return create(code, msg, null);
	}
	
	public static Resp create(int code, String msg, Object data) {
		Resp resp = new Resp();
		resp.code = code;
		resp.msg = msg;
		resp.data = data;
		return resp;
	}
}