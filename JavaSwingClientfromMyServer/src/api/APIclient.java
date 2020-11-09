package api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIclient {

	private static Retrofit retrofit =null;
	
	public static Retrofit getClient()
	{
		
		HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor(); 
		
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		
		OkHttpClient client=new OkHttpClient()			
			  .newBuilder().addInterceptor(interceptor).build();
		
		//System.out.println(client);
		
		retrofit=new Retrofit.Builder()
				.baseUrl("http://localhost:9596/api/")
				.addConverterFactory(GsonConverterFactory.create())
				.client(client)
				.build();
		
		/*
		try {
			OkHttpClient client=new OkHttpClient();
			Request req=new Request.Builder()
					.url("http://localhost:9596/api/product/findall")
					.build();
			 Response response=client.newCall(req).execute();
			 System.out.println(response.body().string());
			 
			 
			 Retrofit retrofit=new Retrofit.Builder()
					 .baseUrl("http://localhost:9596/api/product/")
					 .addConverterFactory(GsonConverterFactory.create())
					 .build();
			 
			 System.out.println(retrofit.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			*/
		
		
		return retrofit;
	}
}
