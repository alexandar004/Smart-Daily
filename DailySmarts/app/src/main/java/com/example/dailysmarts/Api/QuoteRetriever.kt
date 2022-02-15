package com.example.dailysmarts.api

import com.example.dailysmarts.Constant.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object QuoteRetriever {

    private val retrofit = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }


    fun getQuote(language: String): Quote? {
        return apiService.getQuote(language).execute().body()
    }
}
