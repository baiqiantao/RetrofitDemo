package com.github.ikidou.Examples;

import com.github.ikidou.PrintUtils;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

/**
 * Header/Headers注解
 */
public class E04_Header_Headers {
	public interface BlogService {
		@GET("/headers?showAll=true")
		@Headers({"CustomHeader1: customHeaderValue1", "CustomHeader2: customHeaderValue2"})
		Call<ResponseBody> testHeader(@Header("CustomHeader3") String customHeaderValue3);
		
	}
	
	public static void main(String[] args) {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("http://localhost:4567/")
				.build();
		
		BlogService service = retrofit.create(BlogService.class);
		
		//演示 @Headers 和 @Header
		Call<ResponseBody> call1 = service.testHeader("ikidou");
		PrintUtils.print(call1);
	}
}
