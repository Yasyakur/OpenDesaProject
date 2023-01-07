package com.example.opendesa.repository

import com.example.opendesa.api.RetrofitInstance
import com.example.opendesa.model.Berita

class Repository {

    suspend fun getBerita(): List<List<Berita>> {
        return RetrofitInstance.api.getBerita()
    }

    suspend fun getSearch(): List<List<Berita>> {
        return RetrofitInstance.api.getSearch()
    }
}