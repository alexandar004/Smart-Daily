package com.example.dailysmarts.viewModels

import QuoteService
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.dailysmarts.api.Quote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyQuotesViewModel(application: Application) : AndroidViewModel(application) {

    private val _quotesData = MutableLiveData<List<Quote>>()
    var quotesData: LiveData<List<Quote>> = _quotesData
    private lateinit var quoteService: QuoteService


    init {
        initQuoteService()
    }

    private fun initQuoteService() {
        quoteService = QuoteService(getApplication())
    }

    fun onLoadQuote() {
        viewModelScope.launch(Dispatchers.IO) {
            val quotes = quoteService.getAllQuotes()
            _quotesData.postValue(quotes)
        }
    }

    fun deleteQuote(quote: Quote) {
        viewModelScope.launch(Dispatchers.IO) {
            quoteService.deleteQuote(quote)
        }
    }
}