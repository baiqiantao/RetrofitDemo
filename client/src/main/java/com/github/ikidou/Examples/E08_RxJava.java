package com.github.ikidou.Examples;

import com.github.ikidou.entity.Blog;
import com.github.ikidou.entity.Result;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * CallAdapter rxJava
 */
public class E08_RxJava {
	public interface BlogService {
		@GET("/blog")
		Observable<Result<List<Blog>>> getBlogs(@Query("page") int page);
		//如果需要Header的值，可以把返回值替换为 Observable<Response<Result<List<Blog>>>>
	}
	
	public static void main(String[] args) {
		Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd hh:mm:ss")
				.create();
		
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("http://localhost:4567/")
				.addConverterFactory(GsonConverterFactory.create(gson))
				.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
				//.addCallAdapterFactory(RxJava2CallAdapterFactory.create())// 针对rxjava2.x
				.build();
		
		BlogService service = retrofit.create(BlogService.class);
		service.getBlogs(1)
				.observeOn(Schedulers.io())
				.subscribe(new Subscriber<Result<List<Blog>>>() {
					@Override
					public void onCompleted() {
						System.out.println("onCompleted");
					}
					
					@Override
					public void onError(Throwable e) {
						System.err.println("onError");
					}
					
					@Override
					public void onNext(Result<List<Blog>> blogsResult) {
						System.out.println(blogsResult);
					}
				});
	}
}
