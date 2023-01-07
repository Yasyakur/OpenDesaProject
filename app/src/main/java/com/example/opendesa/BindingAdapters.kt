package com.example.opendesa

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.opendesa.model.Berita
import com.example.opendesa.ui.home.BeritaAdapter
import com.example.opendesa.ui.test.BeritaDesaAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Berita>?) {
    val adapter = recyclerView.adapter as BeritaAdapter
    adapter.submitList(data)
}

@BindingAdapter("listBeritaDesa")
fun bundRecyclerView(recyclerView: RecyclerView, data: List<Berita>?) {
    val adapter = recyclerView.adapter as BeritaDesaAdapter
    adapter.submitList(data)
}