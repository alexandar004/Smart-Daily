package com.example.dailysmarts.dataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quote_table")
data class DataBaseQuote(
    @PrimaryKey(autoGenerate = true) var id: Int? = null,
    @ColumnInfo(name = "quoteText") val quoteText: String,
    @ColumnInfo(name = "author") val quoteAuthor: String,
)