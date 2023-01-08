package com.example.opendesa.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.opendesa.databinding.ListBeritaBinding
import com.example.opendesa.model.Berita

class BeritaAdapter : ListAdapter<Berita, BeritaAdapter.BeritaViewHolder>(DiffCallback){
    class BeritaViewHolder(private var binding: ListBeritaBinding) : RecyclerView.ViewHolder(binding.root) {
        lateinit var button: Button
        fun bind(berita: Berita) {
            binding.beritaHome = berita
            button = binding.buttonSelengkapnya
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeritaViewHolder {
        return BeritaViewHolder(ListBeritaBinding.inflate(
            LayoutInflater.from(parent.context)
        ))
    }

    override fun onBindViewHolder(holder: BeritaViewHolder, position: Int) {
        val berita = getItem(position)
        holder.bind(berita)
        holder.button.setOnClickListener {

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