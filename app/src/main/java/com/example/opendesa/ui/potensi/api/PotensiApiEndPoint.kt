package com.example.opendesa.ui.potensi.api

import com.example.opendesa.ui.potensi.data.PotensiModelData
import retrofit2.http.GET
import retrofit2.Response

interface PotensiApiEndPoint {
    @GET("potensi")
    suspend fun getPotensiData(): Response<PotensiModelData>
}