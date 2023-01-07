package com.example.opendesa

import android.os.Build
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.opendesa.model.Berita
import com.example.opendesa.ui.home.BeritaAdapter
import com.example.opendesa.ui.home.BeritaApiStatus
import java.time.Instant
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern


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

@BindingAdapter("imgBeritaHome")
fun bindImgBeritaHome(imgView: ImageView, imgUrl: String?){
    val imgRegex = "(?i)<img[^>]+?src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>"

    val p: Pattern = Pattern.compile(imgRegex)
    val m: Matcher = p.matcher(imgUrl)

    while (m.find()) {
        var imgSrc: String = m.group(1)
        imgUrl?.let{
            val imgUri = imgSrc.toUri().buildUpon().scheme("https").build()
            imgView.load(imgUri) {
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_connection_error)
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@BindingAdapter("dateBeritaHome")
fun bindDateBeritaHome(textView: TextView, date: String?){
    val timeFormatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME

    val offsetDateTime: OffsetDateTime =
        OffsetDateTime.parse(date, timeFormatter)

    val dateString: Date = Date.from(Instant.from(offsetDateTime))
    textView.text = dateString.toLocaleString()
}