package com.example.dailysmarts.api

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//data class Quote(
//    val quoteText: String,
//    val quoteAuthor: String,
//    val senderName: String,
//    val senderLink: String,
//    val quoteLink: String,
//)

@Entity(tableName = "quote_table")
data class Quote(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "quoteText") val quoteText: String,
    @ColumnInfo(name = "author") val quoteAuthor: String,
    @ColumnInfo(name = "senderName") val senderName: String,
    @ColumnInfo(name = "senderLink") val senderLink: String,
    @ColumnInfo(name = "quoteLink") val quoteLink: String,
)