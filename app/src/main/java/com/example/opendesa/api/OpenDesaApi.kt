package com.example.opendesa.api

import com.example.opendesa.model.Berita
import com.example.opendesa.model.UnduhanModel
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Streaming

interface OpenDesaApi {
    @GET("api/berita-desa")
    suspend fun getBerita(): List<List<Berita>>

    @GET("api/unduhan/kategori/{type}")
    suspend fun getUnduhanList(@Path("type") type: String): Response<UnduhanModel>

    @Streaming
    @GET("api/unduhan/{id}")
    suspend fun downloadFile(@Path("id") id: Int): Response<ResponseBody>
}