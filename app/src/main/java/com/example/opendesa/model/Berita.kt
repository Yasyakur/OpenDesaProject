package com.example.opendesa.model

import com.google.gson.annotations.SerializedName

data class Berita(
    @SerializedName("desa_id")
    val desa_id: String,

    @SerializedName("nama_desa")
    val nama_desa: String,

    @SerializedName("feed_link")
    val feed_link: String,

    @SerializedName("link")
    val link: String,

    @SerializedName("date")
    val date: String,

    @SerializedName("author")
    val author: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("image")
    val image: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("content")
    val content: String
)
