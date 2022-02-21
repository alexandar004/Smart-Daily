package com.example.retrofit

import com.google.gson.annotations.SerializedName

class Quote {
    private val id: String? = null

    @SerializedName("en")
    private var text: String? = null
    private var author: String? = null

    fun getId(): String? {return id }
    fun getText(): String? {return text}
    fun getAuthor(): String? {return author}
}