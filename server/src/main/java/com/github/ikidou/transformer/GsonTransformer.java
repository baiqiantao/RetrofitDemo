package com.github.ikidou.transformer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import spark.ResponseTransformer;

public class GsonTransformer implements ResponseTransformer {
	private static GsonTransformer instance = new GsonTransformer();
	private final Gson gson;
	
	private GsonTransformer() {
		this(null);
	}
	
	private GsonTransformer(Gson gson) {
		if (gson == null) {
			gson = new GsonBuilder()
					.disableHtmlEscaping()
					.setDateFormat("yyyy-MM-dd hh:mm:ss")
					.create();
		}
		this.gson = gson;
	}
	
	public static GsonTransformer getDefault() {
		return instance;
	}
	
	public static GsonTransformer newInstance(Gson gson) {
		return new GsonTransformer(gson);
	}
	
	public static GsonTransformer newInstance() {
		return new GsonTransformer();
	}
	
	@Override
	public String render(Object model) throws Exception {
		return gson.toJson(model);
	}
}
