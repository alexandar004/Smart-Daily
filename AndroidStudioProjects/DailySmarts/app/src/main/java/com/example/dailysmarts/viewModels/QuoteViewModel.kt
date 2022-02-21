package com.example.dailysmarts.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailysmarts.api.Quote
import com.example.dailysmarts.api.QuoteRetriever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuoteViewModel: ViewModel() {

    private val service = QuoteRetriever
    private val _quoteData = MutableLiveData<Quote>()
    val quoteData: LiveData<Quote> = _quoteData

    fun getQuote(language: String){
        viewModelScope.launch(Dispatchers.IO) {
            val quote = service.getQuote(language)

            _quoteData.postValue(quote!!)
        }
    }
}