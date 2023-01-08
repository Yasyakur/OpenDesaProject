package com.example.opendesa.repository

import com.example.opendesa.api.RetrofitInstance
import com.example.opendesa.model.Berita
import com.example.opendesa.model.UnduhanModel
import retrofit2.Response

class Repository {

    suspend fun getBerita(): List<List<Berita>> {
        return RetrofitInstance.api.getBerita()
    }

    suspend fun getUnduhanData(type: String) = RetrofitInstance.api.getUnduhanList(type)

    suspend fun downloadFile(url: String, path: String, fileName: String) {
    }
}