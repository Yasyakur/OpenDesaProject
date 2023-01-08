package com.example.opendesa.api

import com.downloader.PRDownloader
import com.downloader.PRDownloaderConfig
import com.example.opendesa.util.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()




object RetrofitInstance {
    val api: OpenDesaApi by lazy {
        retrofit.create(OpenDesaApi::class.java)
    }
}

