package com.example.opendesa.ui.beritadesa

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.opendesa.databinding.ListItemBeritaBinding
import com.example.opendesa.model.Berita


class BeritaDesaAdapter: ListAdapter<Berita, BeritaDesaAdapter.BeritaDesaViewHolder>(DiffCallback){
    class BeritaDesaViewHolder(private var binding: ListItemBeritaBinding) : RecyclerView.ViewHolder(binding.root) {
        lateinit var button: Button
        fun bind(berita: Berita) {
            binding.beritaDesa = berita
            button = binding.buttonDetail
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):BeritaDesaViewHolder{
        return BeritaDesaViewHolder(
            ListItemBeritaBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
    }

    override fun onBindViewHolder(holder:BeritaDesaViewHolder, position: Int) {
        val berita = getItem(position)
        val context = holder.itemView.context
        holder.bind(berita)
        holder.button.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(Uri.parse(berita.link))
            context.startActivity(intent)

        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Berita>() {
        override fun areItemsTheSame(oldItem: Berita, newItem: Berita): Boolean {
            return oldItem.link == newItem.link
        }

        override fun areContentsTheSame(oldItem: Berita, newItem: Berita): Boolean {
            return oldItem.link == newItem.link
        }

    }
}