package com.github.ikidou.entity;

import com.google.gson.annotations.SerializedName;

public class Blog {
	
	@SerializedName("id")
	private long id;
	
	@SerializedName("date")
	private String date;
	
	@SerializedName("author")
	public String author;
	
	@SerializedName("title")
	public String title;
	
	@SerializedName("content")
	public String content;
	
	@Override
	public String toString() {
		return "Blog{" +
				"id=" + id +
				", date='" + date + '\'' +
				", author='" + author + '\'' +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				'}';
	}
}
