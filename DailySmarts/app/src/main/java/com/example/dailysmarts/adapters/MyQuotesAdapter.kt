package com.example.dailysmarts.adapters

import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dailysmarts.R
import com.example.dailysmarts.api.Quote
import com.example.dailysmarts.dataBase.DataBaseQuote
import kotlinx.android.synthetic.main.view_daily_quote_item.view.*

class MyQuotesAdapter(private val savedQuote: List<DataBaseQuote>) :
    RecyclerView.Adapter<MyQuotesAdapter.ViewHolder>() {

    //private val quoteResponse: Quote
    //RecyclerView.Adapter<QuoteAdapter.ViewHolder>()
    //private val quoteList: MutableList<DataBaseQuote> = mutableListOf()


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(quote: DataBaseQuote) {
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
        holder.bind(savedQuote[position])
    }

//    fun setItems(quotes: List<DataBaseQuote>) {
//        quoteList.clear()
//        quoteList.addAll(quotes)
//        notifyDataSetChanged()
//    }

    override fun getItemCount(): Int = savedQuote.size

}

