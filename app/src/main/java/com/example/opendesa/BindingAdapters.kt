package com.example.opendesa

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.opendesa.model.Berita
import com.example.opendesa.ui.home.BeritaAdapter
import com.example.opendesa.ui.home.BeritaApiStatus

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Berita>?) {
    val adapter = recyclerView.adapter as BeritaAdapter
    adapter.submitList(data)
}

@BindingAdapter("beritaApiStatus")
fun bindBeritaStatus(statusImageView: ImageView, status: BeritaApiStatus) {
    when (status) {
        BeritaApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        BeritaApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        BeritaApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}