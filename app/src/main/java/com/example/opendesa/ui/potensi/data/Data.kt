package com.example.opendesa.ui.potensi.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Data
    (val created_at: String, val deskripsi: String, val `file`: String, val id: Int, val kategori: String, val nama: String, val slug: String, val updated_at: String):Parcelable
