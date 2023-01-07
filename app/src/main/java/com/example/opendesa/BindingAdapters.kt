package com.example.opendesa

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.opendesa.model.Berita
import com.example.opendesa.ui.home.BeritaAdapter
import com.example.opendesa.ui.test.BeritaDesaAdapter
import com.example.opendesa.ui.home.BeritaApiStatus
import java.util.regex.Matcher
import java.util.regex.Pattern


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

@BindingAdapter("imgBeritaDesa")
fun bundImgBeritaDesa(imgView: ImageView, imgUrl: String?){
    val imgRegex = "(?i)<img[^>]+?src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>"
    val ptr: Pattern = Pattern.compile(imgRegex)
    val mtc: Matcher = ptr.matcher(imgUrl)

    while (mtc.find()){
        var imgSrcc: String = mtc.group(1)
        imgUrl?.let {
            val imgUri = imgSrcc.toUri().buildUpon().scheme("https").build()
            imgView.load(imgUri) {
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_connection_error)
            }
        }
    }
}