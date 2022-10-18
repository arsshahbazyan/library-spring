package com.epam.learning.library.service;

import java.io.IOException;

import com.epam.learning.library.retrofit.RandomQuoteAPI;
import org.springframework.stereotype.Component;

import com.epam.learning.library.retrofit.model.RandomQuoteResponse;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Component
public class RandomQuoteService {

	public RandomQuoteResponse getRandomResponse() {
		
		RandomQuoteResponse randomQuote = null;
		
		OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
		Retrofit retrofit = new Retrofit.Builder()
		  .baseUrl("https://api.quotable.io/random/")
		  .addConverterFactory(GsonConverterFactory.create())
		  .client(httpClient.build())
		  .build();
		
		RandomQuoteAPI randomQuoteAPI = retrofit.create(RandomQuoteAPI.class);
		Call<RandomQuoteResponse> callSync = randomQuoteAPI.getRandomQuote();
		
		try {
			Response<RandomQuoteResponse> response = callSync.execute();
			randomQuote = response.body();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
		return randomQuote;
	}
	
}
