package com.example.dailysmarts.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dailysmarts.R
import com.example.dailysmarts.api.Quote
import kotlinx.android.synthetic.main.view_daily_quote_item.view.*

class MyQuotesAdapter() :
    RecyclerView.Adapter<MyQuotesAdapter.ViewHolder>() {

    private val listQuote: MutableList<Quote> = mutableListOf()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(quote: Quote) {
            itemView.txtQuote.text = quote.quoteText
            itemView.authorName.text = quote.quoteAuthor

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_daily_quote_item, parent, false)
        )
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

