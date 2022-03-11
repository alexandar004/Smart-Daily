package com.example.dailysmarts.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dailysmarts.api.Quote
import com.example.dailysmarts.databinding.ViewDailyQuoteItemBinding
import kotlinx.android.synthetic.main.view_daily_quote_item.view.*

class DailyQuoteAdapter(private val quoteResponse: Quote, private val itemListener: ItemListener) :
    RecyclerView.Adapter<DailyQuoteAdapter.ViewHolder>() {

    private lateinit var binding: ViewDailyQuoteItemBinding

    inner class ViewHolder(
        view: View,
        private val binding: ViewDailyQuoteItemBinding,
        private val itemListener: ItemListener,
    ) : RecyclerView.ViewHolder(view) {
        fun bind(quote: Quote) {
            itemView.txtQuote.text = quote.quoteText
            itemView.authorName.text = quote.quoteAuthor

            binding.btnSave.setOnClickListener {
                itemListener.onSavedQuote(quote)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding =
            ViewDailyQuoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root, binding, itemListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(quoteResponse)
    }

    override fun getItemCount(): Int = 1

}

interface ItemListener {
    fun onSavedQuote(item: Quote)
}
