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
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Converter Gson 反序列化
 */
public class E06_Converter_Gson {
	public interface BlogService {
		@GET("blog/{id}")
		Call<Result<Blog>> getBlog(@Path("id") int id);
	}
	
	public static void main(String[] args) {
		Gson gson = new GsonBuilder()//配置你的Gson
				.setDateFormat("yyyy-MM-dd hh:mm:ss")
				.create();
		
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("http://localhost:4567/")
				.addConverterFactory(GsonConverterFactory.create(gson))//可以接收自定义的Gson，当然也可以不传
				.build();
		
		BlogService service = retrofit.create(BlogService.class);
		Call<Result<Blog>> call = service.getBlog(2);
		call.enqueue(new Callback<Result<Blog>>() {
			@Override
			public void onResponse(Call<Result<Blog>> call, Response<Result<Blog>> response) {// 已经转换为想要的类型了
				Result<Blog> result = response.body();
				System.out.println(result);
			}
			
			@Override
			public void onFailure(Call<Result<Blog>> call, Throwable t) {
				t.printStackTrace();
			}
		});
	}
}
