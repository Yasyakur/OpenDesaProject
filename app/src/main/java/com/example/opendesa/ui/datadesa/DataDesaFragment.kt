package com.example.opendesa.ui.datadesa

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.opendesa.R
import com.example.opendesa.databinding.FragmentDataDesaBinding

class DataDesaFragment : Fragment()
{
    private var _binding: FragmentDataDesaBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: DataDesaAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataDesa : List<Desa>

    private val viewModel: DataDesaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDataDesaBinding.inflate(inflater, container, false)
        val root : View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context)

        viewModel.dataDesa.observe(this.viewLifecycleOwner){
            dataDesa = it
            Log.d("Err", dataDesa.toString())

            if (dataDesa.isEmpty()) {
                val textKosong: TextView = view.findViewById(R.id.tv_data_kosong)
                textKosong.text = "Tidak ada data yang ditemukan!"
            }

            recyclerView = view.findViewById(R.id.recyclerview_desa)
            recyclerView.layoutManager = layoutManager
            recyclerView.setHasFixedSize(true)
            adapter = DataDesaAdapter(dataDesa, activity)
            recyclerView.adapter = adapter

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}