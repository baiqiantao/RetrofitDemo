package com.github.ikidou.Examples;

import com.github.ikidou.entity.Blog;
import com.github.ikidou.entity.Result;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Converter 序列化
 */
public class E07_Converter_Gson {
	public interface BlogService {
		@POST("blog")
		Call<Result<Blog>> createBlog(@Body Blog blog);
	}
	
	public static void main(String[] args) {
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd hh:mm:ss")
				.create();
		
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("http://localhost:4567/")
				.addConverterFactory(GsonConverterFactory.create(gson))
				.build();
		
		BlogService service = retrofit.create(BlogService.class);
		Blog blog = new Blog("author：包青天", "title：测试", "content：新建的Blog");
		Call<Result<Blog>> call = service.createBlog(blog);
		call.enqueue(new Callback<Result<Blog>>() {
			@Override
			public void onResponse(Call<Result<Blog>> call, Response<Result<Blog>> response) {
				Result<Blog> result = response.body();// 已经转换为想要的类型了
				System.out.println(result);
			}
			
			@Override
			public void onFailure(Call<Result<Blog>> call, Throwable t) {
				t.printStackTrace();
			}
		});
	}
}
