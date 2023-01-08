package com.example.opendesa.ui.datadesa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.opendesa.R

class DataDesaAdapter(private val listDesa: List<Desa>, private val activity: FragmentActivity?)
    : RecyclerView.Adapter<DataDesaAdapter.ViewHolder>()
{
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val button : Button = view.findViewById(R.id.button_desa)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.desa_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = listDesa[position]

        holder.button.text = currentItem.namaDesa

        holder.button.setOnClickListener {
            val showDetail = DetailDesaFragment(currentItem)
            showDetail.show((activity as AppCompatActivity).supportFragmentManager, "showDetail")
        }
    }

    override fun getItemCount(): Int = listDesa.size
}