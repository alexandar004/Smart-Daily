package com.example.retrofit

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    private var instance: ApiClient? = null
    private var service: QuoteService? = null

    private fun apiClient() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://programming-quotes-api.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(QuoteService::class.java)
    }

    fun getInstance(): com.example.retrofit.ApiClient? {
        if (instance == null) instance = com.example.retrofit.ApiClient()
        return instance
    }

    fun getRandomQuote(listener: ApiListener) {
        service!!.getRandomQuote().enqueue(object : Callback<Quote> {
            override fun onResponse(call: Call<Quote>, response: Response<Quote>) {
                if (response.isSuccessful) {
                    listener.onQuoteReceived(response.body()!!.getText())
                } else {
                    listener.onFailure()
                }
            }

            override fun onFailure(call: Call<Quote>, t: Throwable) {
                listener.onFailure()
            }
        })
    }
}

interface ApiListener {
    fun onQuoteReceived(quote: String?)
    fun onFailure()
}