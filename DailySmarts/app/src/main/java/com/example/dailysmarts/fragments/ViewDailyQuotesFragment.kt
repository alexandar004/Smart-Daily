package com.example.dailysmarts.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dailysmarts.adapters.QuoteAdapter
import com.example.dailysmarts.api.Quote
import com.example.dailysmarts.databinding.FragmentViewDailyQuotesBinding
import com.example.dailysmarts.viewModels.QuoteViewModel

class ViewDailyQuotesFragment : Fragment() {

    private lateinit var binding: FragmentViewDailyQuotesBinding
    private lateinit var quoteViewModel: QuoteViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: QuoteAdapter
    private lateinit var quoteText: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentViewDailyQuotesBinding.inflate(inflater, container, false)
        quoteText = binding.quoteText

        fetchQuote()
        getNewQuote()
        setRecyclerView()

        return binding.root
    }


    private fun fetchQuote() {
        quoteViewModel = ViewModelProvider(this)[QuoteViewModel::class.java]
        val enLang = "en"
        val ruLang = "ru"
        var mainLang = ""
//        ru.seton{
//            mainLang = ruLang
//        }
        quoteViewModel.getQuote(enLang) // Взимаш въпроса
        quoteViewModel.quoteData.observe(viewLifecycleOwner) { quote -> // Обхождаш livedata-та;  quote.quoteAuthor

            binding.refreshSwipe.isRefreshing = false
            quoteText.text = quote.quoteText
            val currentQuote = Quote(quoteText = quote.quoteText,
                quoteAuthor = quote.quoteAuthor,
                senderName = quote.senderName,
                senderLink = quote.senderLink,
                quoteLink = quote.quoteLink)

            adapter = QuoteAdapter(currentQuote)
            recyclerView.adapter = adapter

        }
    }

    private fun setRecyclerView() {
        recyclerView = binding.recycleView
        recyclerView.layoutManager = LinearLayoutManager(this.context)
    }

    private fun getNewQuote() {
        binding.refreshSwipe.setOnRefreshListener {
            fetchQuote()
        }
    }
}