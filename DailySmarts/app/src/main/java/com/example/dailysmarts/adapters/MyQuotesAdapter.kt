package com.example.dailysmarts.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dailysmarts.api.Quote
import com.example.dailysmarts.databinding.ViewDailyQuoteItemBinding
import kotlinx.android.synthetic.main.view_daily_quote_item.view.*

class MyQuotesAdapter(private val deleteListener: DeleteItemListener) :
    RecyclerView.Adapter<MyQuotesAdapter.ViewHolder>() {

    private lateinit var binding: ViewDailyQuoteItemBinding
    private val listQuote: MutableList<Quote> = mutableListOf()

    inner class ViewHolder(
        view: View, private val binding: ViewDailyQuoteItemBinding,
        private val deleteItem: DeleteItemListener,
    ) : RecyclerView.ViewHolder(view) {
        fun bind(quote: Quote) {
            itemView.txtQuote.text = quote.quoteText
            itemView.authorName.text = quote.quoteAuthor


            binding.btnSave.setOnClickListener {
                deleteItem.onDeleteQuote(quote)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyQuotesAdapter.ViewHolder {
        binding =
            ViewDailyQuoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root, binding, deleteListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listQuote[position])
    }

    fun setItems(quotes: List<Quote>) {
        listQuote.clear()
        listQuote.addAll(quotes)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = listQuote.size
}

interface DeleteItemListener {
    fun onDeleteQuote(item: Quote)
}