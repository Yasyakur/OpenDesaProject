package com.example.opendesa.ui.unduhan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.opendesa.R
import com.example.opendesa.databinding.UnduhanItemBinding
import com.example.opendesa.databinding.UnduhanSelectorLayoutBinding
import com.example.opendesa.model.UnduhanItem

class UnduhanItemAdapter(val listener: OnUnduhanRVClickListener) :
    RecyclerView.Adapter<UnduhanItemAdapter.ViewHolder>() {

    private val data: ArrayList<UnduhanItem> = arrayListOf()
    private var selectedItem = -1

    fun setData(typeList: List<UnduhanItem>) {
        data.clear()
        data.addAll(typeList)
        notifyDataSetChanged()
    }
    fun setSelectedItem(index : Int){
        selectedItem = index
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = UnduhanItemBinding.bind(view)

        fun bind(
            unduhanItem: UnduhanItem,
            position: Int
        ) {
            binding.fileName.text = unduhanItem.file?.substringAfter('/')
            binding.itemName.text = unduhanItem.nama
            binding.downloadBtn.setOnClickListener{listener.onUnduhanClick(position,unduhanItem)}

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.unduhan_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position],position)
    }

    override fun getItemCount(): Int = data.size
}

interface OnUnduhanRVClickListener{
    fun onUnduhanClick(position: Int,unduhanItem: UnduhanItem)
}