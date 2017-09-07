package com.github.ikidou;

import com.github.ikidou.db.DB;
import com.github.ikidou.handler.BlogHandler;
import com.github.ikidou.handler.FormHandler;
import com.github.ikidou.handler.HeaderHandler;
import com.github.ikidou.transformer.GsonTransformer;
import com.google.gson.Gson;

import java.sql.SQLException;

import spark.Spark;

public class RESTServer {
	private static final String TYPE = "application/json; charset=UTF-8";
	
	/**
	 * 启动服务器
	 */
	public static void main(String[] args) throws SQLException {
		DB.init();
		
		Spark.init();
		
		Spark.get("/blog", BlogHandler.GET, GsonTransformer.getDefault());
		Spark.get("/blog/:id", BlogHandler.GET, GsonTransformer.getDefault());
		
		Spark.post("/blog", BlogHandler.POST, GsonTransformer.getDefault());
		
		Spark.put("/blog", BlogHandler.PUT, GsonTransformer.getDefault());
		Spark.put("/blog/:id", BlogHandler.PUT, GsonTransformer.getDefault());
		
		Spark.delete("/blog", BlogHandler.DELETE, GsonTransformer.getDefault());
		Spark.delete("/blog/:id", BlogHandler.DELETE, GsonTransformer.getDefault());
		
		Spark.head("/blog", BlogHandler.HEAD, GsonTransformer.getDefault());
		Spark.head("/blog/:id", BlogHandler.HEAD, GsonTransformer.getDefault());
		
		Spark.post("/form", new FormHandler(), GsonTransformer.getDefault());
		
		Spark.get("/headers", new HeaderHandler(), GsonTransformer.getDefault());
		
		Spark.after((request, response) -> {
			response.type(TYPE);
			response.header("author", "ikidou");
		});
		
		Spark.exception(RuntimeException.class, (exception, request, response) -> {
			Gson gson = new Gson();
			response.body(gson.toJson(Resp.create(400, exception.getMessage())));
		});
		Spark.exception(Exception.class, (exception, request, response) -> {
			Gson gson = new Gson();
			response.body(gson.toJson(Resp.create(500, exception.getMessage())));
		});
	}
}
