package com.example.opendesa.ui.datadesa

import com.squareup.moshi.Json

data class Desa (val id: String,
                 @Json(name = "kode_desa") val kodeDesa : String,
                 @Json(name = "nama_desa") val namaDesa : String,
                 val website : String,
                 @Json(name = "luas_wilayah") val luasWilayah : Double)