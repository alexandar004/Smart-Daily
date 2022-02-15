package com.example.dailysmarts.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dailysmarts.api.Quote
import com.example.dailysmarts.R
import kotlinx.android.synthetic.main.view_daily_quote_item.view.*

class QuoteAdapter(private val quoteResponse: Quote) :
    RecyclerView.Adapter<QuoteAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            fun bind(quote: Quote){
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
        holder.bind(quoteResponse)
    }

    override fun getItemCount(): Int = 1

}