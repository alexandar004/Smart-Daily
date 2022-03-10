package com.example.dailysmarts.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/api/1.0/?method=getQuote&format=json")
    fun getQuote(@Query("lang") language: String): Call<Quote>
}