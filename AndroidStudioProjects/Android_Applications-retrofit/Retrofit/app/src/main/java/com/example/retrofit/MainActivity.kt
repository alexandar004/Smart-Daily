package com.example.retrofit

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.retrofit.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.btnGetQuote.setOnClickListener {
            onGetQuoteClicked()
        }
    }

    private fun onGetQuoteClicked() {
       ApiClient.getInstance()?.getRandomQuote(object : ApiListener {
            override fun onQuoteReceived(quote: Quote) {
                binding.txtQuote.text = quote.getText()
            }

            override fun onFailure() {
                Toast.makeText(this@MainActivity, "Something happened", Toast.LENGTH_LONG).show()
            }
        })
    }
}