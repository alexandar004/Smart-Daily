package com.example.dailysmarts.dataBase

import androidx.room.*

@Dao
interface QuoteDao {

    @Query("SELECT * FROM quote_table")
    suspend fun getAllQuotes(): List<DataBaseQuote>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuote(quote: DataBaseQuote)

    @Delete
    suspend fun deleteQuote(quote: DataBaseQuote)
}