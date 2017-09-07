package com.github.ikidou.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable
public class Blog {
	
	@DatabaseField(generatedId = true)
	private long id;
	
	@DatabaseField
	private Date date = new Date();
	
	@DatabaseField(canBeNull = false)
	public String author;
	
	@DatabaseField(canBeNull = false)
	public String title;
	
	@DatabaseField(canBeNull = false)
	public String content;
	
	@Override
	public String toString() {
		return "Blog{" +
				"id=" + id +
				", date=" + date +
				", author='" + author + '\'' +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				'}';
	}
}
