package com.example.dailysmarts.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
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

    //private lateinit var quoteService: QuoteService
    private lateinit var recyclerView: RecyclerView
    private lateinit var refreshMyQuote: SwipeRefreshLayout
    private lateinit var binding: FragmentMyQuotesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentMyQuotesBinding.inflate(inflater, container, false)

        loadMovie()

        refreshMyQuote = binding.refreshMyQuotes

        setRecyclerView()

        refreshMyQuote.setOnRefreshListener {
            myQuotesViewModel.onLoadQuote()
            myQuotesViewModel.quotesData.observe(viewLifecycleOwner) {
                val adapter = MyQuotesAdapter(it)
                recyclerView.adapter = adapter
                refreshMyQuote.isRefreshing = false
            }
        }

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

    private fun setRecyclerView() {
        recyclerView = binding.recycleViewMyQuotes
        recyclerView.layoutManager = LinearLayoutManager(this.context)
    }
}


//        btnHeart.setOnClickListener{deletedQuoteListener()}

//        myQuotesViewModel.quotesData.observe(viewLifecycleOwner) { quotes -> // Работа с дата-та
//
//            adapter.setItems(quotes)
//        }