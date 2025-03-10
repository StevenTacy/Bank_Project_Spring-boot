package com.example.demo.service;

import java.io.IOException;


import org.springframework.stereotype.Service;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
@Service
public class HttpService {
	   public static final MediaType JSON
	    = MediaType.get("application/json; charset=utf-8");

		OkHttpClient client = new OkHttpClient();

		public String post(String url, String json) throws IOException {
		  RequestBody body = RequestBody.create(json,JSON);
		  Request request = new Request.Builder()
			  .url(url)
			  .post(body)
			  .build();
		  try (Response response = client.newCall(request).execute()) {
			return response.body().string();
		  }
		}
}
