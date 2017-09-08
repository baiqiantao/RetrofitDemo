package com.github.ikidou.Examples;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class E01_Path {
	public interface BlogService {
		@GET("blog/{id}")	/*这里的{id} 表示是一个变量*/
		Call<ResponseBody> getBlog(/*这里的id表示的是上面的{id}*/@Path("id") int id);
	}
	
	public static void main(String[] args) throws IOException {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("http://localhost:4567/")
				.build();
		
		BlogService service = retrofit.create(BlogService.class);
		Call<ResponseBody> call = service.getBlog(2);
		/*用法和OkHttp的call如出一辙，不同的是如果是Android系统回调方法执行在主线程*/
		call.enqueue(new Callback<ResponseBody>() {
			@Override
			public void onResponse(
					Call<ResponseBody> call, Response<ResponseBody> response) {
				try {
					System.out.println(response.body().string());
					System.out.println(response.code() + "  " + response.isSuccessful());//200  true
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			@Override
			public void onFailure(Call<ResponseBody> call, Throwable t) {
				t.printStackTrace();
			}
		});
	}
}
