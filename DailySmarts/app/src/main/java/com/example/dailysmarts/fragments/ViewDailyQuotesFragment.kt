package com.example.dailysmarts.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
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
    private lateinit var bindingViewDailyQuoteItem: ViewDailyQuoteItemBinding
    private lateinit var quoteViewModel: QuoteViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DailyQuoteAdapter
    private lateinit var heartButton: ImageButton

    private var textQuote: String = ""
    private var authorQuote: String = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        bindingFragmentViewDailyQuotes =
            FragmentViewDailyQuotesBinding.inflate(inflater, container, false)
        bindingViewDailyQuoteItem = ViewDailyQuoteItemBinding.inflate(inflater, container, false)
//        quoteService = QuoteService.getInstance(requireContext())

        fetchQuote()
        getNewQuote()
        setRecyclerView()
        saveQuote(bindingViewDailyQuoteItem)


        return bindingFragmentViewDailyQuotes.root
    }

    private fun saveQuote(binding: ViewDailyQuoteItemBinding) {
        heartButton = binding.btnSave
        heartButton.setOnClickListener {
//            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {

            textQuote = txtQuote.text.toString()
            authorQuote = authorName.text.toString()

            val quote = Quote(quoteText = textQuote,
                quoteAuthor = authorQuote,
                senderName = "",
                senderLink = "",
                quoteLink = ""
            )

            viewDailyQuoteViewModel.insertQuote(quote)
//            }
        }
    }

    private fun shareQuoteInSocialMedia() {
        btnShare.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
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

            bindingFragmentViewDailyQuotes.refreshSwipe.isRefreshing = false
            //quoteText.text = quote.quoteText
            val currentQuote = Quote(
                quoteText = quote.quoteText,
                quoteAuthor = quote.quoteAuthor,
                senderName = quote.senderName,
                senderLink = quote.senderLink,
                quoteLink = quote.quoteLink
            )

            adapter = DailyQuoteAdapter(currentQuote, this)
            recyclerView.adapter = adapter

        }
    }

    private fun setRecyclerView() {
        recyclerView = bindingFragmentViewDailyQuotes.recycleView
        recyclerView.layoutManager = LinearLayoutManager(this.context)
    }

    private fun getNewQuote() {
        bindingFragmentViewDailyQuotes.refreshSwipe.setOnRefreshListener {
            fetchQuote()
        }
    }

    override fun onSavedQuote(item: Quote) {
        viewDailyQuoteViewModel.insertQuote(item)
    }

//    private fun clickBtnHeartListener() {
//        var ifLike: Boolean
//
//        if (btnHeart.setOnClickListener)
//    }

    //            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
//
//                textQuote = txtQuote.text.toString()
//                authorQuote = authorName.text.toString()
//
//                val quotes =
//                    DataBaseQuote(
//                        quoteText = textQuote,
//                        quoteAuthor = authorQuote,
//                    )
//                quoteService.addQuote(quotes)
//            }
}
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.lifecycle.ViewModelProvider
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.example.dailysmarts.adapters.DailyQuoteAdapter
//import com.example.dailysmarts.api.Quote
//import com.example.dailysmarts.databinding.FragmentViewDailyQuotesBinding
//import com.example.dailysmarts.databinding.ViewDailyQuoteItemBinding
//import com.example.dailysmarts.viewModels.QuoteViewModel
//import com.example.dailysmarts.viewModels.ViewDailyQuotesViewModel
//
//class ViewDailyQuotesFragment : Fragment() {
//
//    private val viewDailyQuoteViewModel by lazy {
//        ViewModelProvider(this)[ViewDailyQuotesViewModel::class.java]
//    }
//
//    private lateinit var bindingFragmentViewDailyQuotes: FragmentViewDailyQuotesBinding
//    private lateinit var bindingViewDailyQuoteItem: ViewDailyQuoteItemBinding
//    private lateinit var quoteViewModel: QuoteViewModel
//    private lateinit var recyclerView: RecyclerView
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?,
//    ): View? {
//        bindingFragmentViewDailyQuotes =
//            FragmentViewDailyQuotesBinding.inflate(inflater, container, false)
//
//        bindingViewDailyQuoteItem =
//            ViewDailyQuoteItemBinding.inflate(inflater, container, false)
//
//        return bindingFragmentViewDailyQuotes.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        setRecyclerView()
//        fetchQuote()
//        getNewQuote()
//
//    }
//
//
//    private fun saveQuote(quote: Quote) {
//        viewDailyQuoteViewModel.insertQuote(quote)
//    }
//
//    private fun fetchQuote() {
//        quoteViewModel = ViewModelProvider(this)[QuoteViewModel::class.java]
//        val enLang = "en"
//        val ruLang = "ru"
//        var mainLang = ""
//
//        quoteViewModel.getQuote(enLang)
//        quoteViewModel.quoteData.observe(viewLifecycleOwner) { quote ->
//
//            bindingFragmentViewDailyQuotes.refreshSwipe.isRefreshing = false
//
//            val currentQuote = Quote(
//                quoteText = quote.quoteText,
//                quoteAuthor = quote.quoteAuthor,
//                senderName = quote.senderName,
//                senderLink = quote.senderLink,
//                quoteLink = quote.quoteLink
//            )
//            saveQuote(currentQuote)
//        }
//    }
//
//    private fun setRecyclerView() {
//        recyclerView = bindingFragmentViewDailyQuotes.recycleView
//        recyclerView.layoutManager = LinearLayoutManager(this.context)
//    }
//
//    private fun getNewQuote() {
//        bindingFragmentViewDailyQuotes.refreshSwipe.setOnRefreshListener {
//            fetchQuote()
//        }
//    }
//}