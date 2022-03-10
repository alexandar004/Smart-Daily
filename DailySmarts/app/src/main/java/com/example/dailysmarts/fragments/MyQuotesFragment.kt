package com.example.dailysmarts.fragments

import android.opengl.Visibility
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
import com.example.dailysmarts.api.Quote
import com.example.dailysmarts.databinding.FragmentMyQuotesBinding
import com.example.dailysmarts.viewModels.MyQuotesViewModel
import kotlinx.android.synthetic.main.view_daily_quote_item.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyQuotesFragment : Fragment() {

    private val myQuotesViewModel by lazy {
        ViewModelProvider(this)[MyQuotesViewModel::class.java]
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var refreshMyQuote: SwipeRefreshLayout
    private lateinit var binding: FragmentMyQuotesBinding
    private var adapter = MyQuotesAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentMyQuotesBinding.inflate(inflater, container, false)

        setRecyclerView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        refreshMyQuote = binding.refreshMyQuotes


        refreshMyQuote.setOnRefreshListener {
            myQuotesViewModel.onLoadQuote()
            myQuotesViewModel.quotesData.observe(viewLifecycleOwner) {

                recyclerView.adapter = adapter

                adapter.setItems(it)
            }
            refreshMyQuote.isRefreshing = false
        }
    }

    private fun setRecyclerView() {
        recyclerView = binding.recyclerViewMyQuotes
        recyclerView.layoutManager = LinearLayoutManager(this.context)
    }


    private fun deletedQuoteListener(quote: Quote) {
        btnSave.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                myQuotesViewModel.deleteQuote(quote)
            }
        }
    }
}
