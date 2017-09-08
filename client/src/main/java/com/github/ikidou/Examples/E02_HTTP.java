package com.github.ikidou.Examples;

import com.github.ikidou.PrintUtils;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.HTTP;
import retrofit2.http.Path;

/**
 * HTTP注解
 */
public class E02_HTTP {
	public interface BlogService {
		/**
		 * method 表示请求的方法，区分大小写，retrofit 不会做处理
		 * path表示路径
		 * hasBody表示是否有请求体，默认为false，此时可以不写此参数
		 */
		@HTTP(method = "GET", path = "blog/{id}", hasBody = false)
		Call<ResponseBody> getBlog(@Path("id") int id);
	}
	
	public static void main(String[] args) {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("http://localhost:4567/")
				.build();
		
		BlogService service = retrofit.create(BlogService.class);
		Call<ResponseBody> call = service.getBlog(2);
		PrintUtils.print(call);
	}
}
