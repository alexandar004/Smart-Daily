package com.example.dailysmarts.viewModels

import QuoteService
import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailysmarts.api.Quote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewDailyQuotesViewModel() : ViewModel() {

    private lateinit var quoteService: QuoteService

    init {
        initQuoteService()
    }

    private fun initQuoteService() {
        quoteService = QuoteService(context)
    }


    fun insertQuote(quote: Quote) {
        viewModelScope.launch(Dispatchers.IO) {
            quoteService.addQuote(quote)
        }
    }
}