package com.example.dailysmarts.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dailysmarts.adapters.DailyQuoteAdapter
import com.example.dailysmarts.adapters.ItemListener
import com.example.dailysmarts.api.Quote
import com.example.dailysmarts.databinding.FragmentViewDailyQuotesBinding
import com.example.dailysmarts.databinding.ViewDailyQuoteItemBinding
import com.example.dailysmarts.viewModels.QuoteViewModel
import com.example.dailysmarts.viewModels.ViewDailyQuotesViewModel
import kotlinx.android.synthetic.main.view_daily_quote_item.*

class ViewDailyQuotesFragment : Fragment(), ItemListener {

    private val viewDailyQuoteViewModel by lazy {
        ViewModelProvider(this)[ViewDailyQuotesViewModel::class.java]
    }

    private lateinit var bindingFragmentViewDailyQuotes: FragmentViewDailyQuotesBinding
    private lateinit var binding: ViewDailyQuoteItemBinding
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        bindingFragmentViewDailyQuotes =
            FragmentViewDailyQuotesBinding.inflate(inflater, container, false)
        binding =
            ViewDailyQuoteItemBinding.inflate(inflater, container, false)
        return bindingFragmentViewDailyQuotes.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchQuote()
        getNewQuote()
        setRecyclerView()
        onButtonSaveClicked()
    }

    private fun saveQuote() {
        val textQuote = txtQuote.text.toString()
        val authorQuote = authorName.text.toString()
        val quote = Quote(quoteText = textQuote,
            quoteAuthor = authorQuote,
            senderName = "",
            senderLink = "",
            quoteLink = ""
        )
        viewDailyQuoteViewModel.insertQuote(quote)
    }

    private fun fetchQuote() {
        val quoteViewModel = ViewModelProvider(this)[QuoteViewModel::class.java]
        val enLang = "en"
        val ruLang = "ru"
        var mainLang = ""
        quoteViewModel.getQuote(enLang)
        quoteViewModel.quoteData.observe(viewLifecycleOwner) { quote ->
            bindingFragmentViewDailyQuotes.refreshSwipe.isRefreshing = false
            val currentQuote = Quote(
                quoteText = quote.quoteText,
                quoteAuthor = quote.quoteAuthor,
                senderName = quote.senderName,
                senderLink = quote.senderLink,
                quoteLink = quote.quoteLink
            )
            val adapter = DailyQuoteAdapter(currentQuote, this)
            recyclerView.adapter = adapter
        }
    }

    private fun setRecyclerView() {
        recyclerView = bindingFragmentViewDailyQuotes.recycleView
        recyclerView.layoutManager = LinearLayoutManager(this.context)
    }

    private fun getNewQuote() =
        bindingFragmentViewDailyQuotes.refreshSwipe.setOnRefreshListener { fetchQuote() }

    private fun onButtonSaveClicked() = binding.btnSave.setOnClickListener { saveQuote() }


    override fun onSavedQuote(item: Quote) = viewDailyQuoteViewModel.insertQuote(item)
}