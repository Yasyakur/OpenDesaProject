package com.example.opendesa.ui.potensi.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PotensiApi {
    fun getInstance() : Retrofit {
        val urlApi = "http://10.0.2.2:8000/api/"
/*
192.168.212.138
192.168.68.108
 */

        return Retrofit.Builder().baseUrl(urlApi)
            ///return Retrofit.Builder().baseUrl("http://192.168.68.108:8000/api/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}