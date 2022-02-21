package com.example.dailysmarts.viewModels

import QuoteService
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailysmarts.dataBase.DataBaseQuote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewDailyQuotesViewModel(application: Application) : AndroidViewModel(application) {

    private lateinit var quoteService: QuoteService

    init {
        initQuoteService()
    }

    private fun initQuoteService() {
        quoteService = QuoteService(getApplication())
    }

    fun insertQuote(quote: DataBaseQuote) {
        viewModelScope.launch(Dispatchers.IO) {
            quoteService.addQuote(quote)
        }
    }
}