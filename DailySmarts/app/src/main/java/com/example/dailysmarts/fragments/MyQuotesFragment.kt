package com.example.dailysmarts.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dailysmarts.adapters.DeleteItemListener
import com.example.dailysmarts.adapters.MyQuotesAdapter
import com.example.dailysmarts.api.Quote
import com.example.dailysmarts.databinding.FragmentMyQuotesBinding
import com.example.dailysmarts.viewModels.MyQuotesViewModel

class MyQuotesFragment : Fragment(), DeleteItemListener {

    private val myQuotesViewModel by lazy {
        ViewModelProvider(this)[MyQuotesViewModel::class.java]
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: FragmentMyQuotesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMyQuotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        setRefreshIndicator()
    }

    private fun setRefreshIndicator() {
        val refreshMyQuote = binding.refreshMyQuotes
        refreshMyQuote.setOnRefreshListener {
            myQuotesViewModel.onLoadQuote()
            myQuotesViewModel.quotesData.observe(viewLifecycleOwner) {
                val adapter = MyQuotesAdapter(this)
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

    override fun onDeleteQuote(item: Quote) {
        myQuotesViewModel.deleteQuote(item)
    }
}
