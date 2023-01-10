package com.example.opendesa.ui.unduhan

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.opendesa.R
import com.example.opendesa.databinding.UnduhanSelectorLayoutBinding
import kotlin.concurrent.fixedRateTimer

class UnduhanSelectorAdapter(val listener: OnRVClickListener) :
    RecyclerView.Adapter<UnduhanSelectorAdapter.ViewHolder>() {

    private val data: ArrayList<String> = arrayListOf()
    private var selectedItem = -1

    fun setData(typeList: List<String>) {
        data.clear()
        data.addAll(typeList)
        notifyDataSetChanged()
    }
    fun setSelectedItem(index : Int){
        selectedItem = index
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = UnduhanSelectorLayoutBinding.bind(view)

        fun bind(
            typeText: String,
            position: Int
        ) {
            binding.unduhanType.text = typeText
            if (selectedItem == position) {
                binding.unduhanType.setBackgroundColor(Color.parseColor("#043E73"))
                binding.unduhanType.setTextColor(Color.WHITE)
            }else{
                binding.unduhanType.setBackgroundColor(Color.WHITE)
                binding.unduhanType.setTextColor(Color.BLACK)
            }
            binding.unduhanType.setOnClickListener {
                listener.onClick(position,typeText)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.unduhan_selector_layout, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position],position)
    }

    override fun getItemCount(): Int = data.size
}

interface OnRVClickListener{
    fun onClick(position: Int,text : String)
}