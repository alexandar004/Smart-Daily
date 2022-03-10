package com.example.dailysmarts.dataBase

import androidx.room.*
import com.example.dailysmarts.api.Quote

@Dao
interface QuoteDao {

    @Query("SELECT * FROM quote_table")
    suspend fun getAllQuotes(): List<Quote>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuote(quote: Quote)

    @Delete
    suspend fun deleteQuote(quote: Quote)
}