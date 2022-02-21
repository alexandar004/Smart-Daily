package com.example.dailysmarts.fragments

import QuoteService
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.dailysmarts.adapters.MyQuotesAdapter
import com.example.dailysmarts.dataBase.DataBaseQuote
import com.example.dailysmarts.databinding.FragmentMyQuotesBinding
import com.example.dailysmarts.viewModels.MyQuotesViewModel
import kotlinx.android.synthetic.main.view_daily_quote_item.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyQuotesFragment : Fragment() {

    private val myQuotesViewModel by lazy {
        ViewModelProvider(this)[MyQuotesViewModel::class.java]
    }
    private lateinit var quoteService: QuoteService

    private lateinit var binding: FragmentMyQuotesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentMyQuotesBinding.inflate(inflater, container, false)

        loadMovie()

//        binding.refreshMyQuotes.setOnRefreshListener {

//        myQuotesViewModel.quotesData.observe(viewLifecycleOwner) {
//            val adapter = MyQuotesAdapter()
//            adapter.setItems(it)
//        }
//            binding.refreshMyQuotes.isRefreshing = false
//        }


        return binding.root
    }

    private fun deletedQuoteListener(quote: DataBaseQuote) {
        btnHeart.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                myQuotesViewModel.deleteQuote(quote)
            }
        }
    }

    private fun loadMovie() {
        viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
            myQuotesViewModel.onLoadQuote()
        }
    }
}


//        btnHeart.setOnClickListener{deletedQuoteListener()}

//        myQuotesViewModel.quotesData.observe(viewLifecycleOwner) { quotes -> // Работа с дата-та
//
//            adapter.setItems(quotes)
//        }