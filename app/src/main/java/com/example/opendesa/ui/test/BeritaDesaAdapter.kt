package com.example.opendesa.ui.test

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.opendesa.databinding.ListBeritaBinding
import com.example.opendesa.databinding.ListItemBeritaBinding
import com.example.opendesa.model.Berita
import kotlinx.coroutines.NonDisposableHandle.parent

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
        holder.bind(berita)
        holder.button.setOnClickListener {
            Log.d("String", berita.link)
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