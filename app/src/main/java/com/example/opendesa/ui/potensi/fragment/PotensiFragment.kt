package com.example.opendesa.ui.potensi.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.opendesa.ui.potensi.api.PotensiApi
import com.example.opendesa.ui.potensi.api.PotensiApiEndPoint
import com.example.opendesa.R
import com.example.opendesa.databinding.FragmentPotensiBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class PotensiFragment : Fragment(R.layout.fragment_potensi) {

    private lateinit var binding: FragmentPotensiBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        getPotensiList()
        binding = FragmentPotensiBinding.inflate(layoutInflater)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title = "Potensi Desa"

        binding.potensiCat1.setOnClickListener {
            filterList("kecamatan")
        }
        binding.potensiCat2.setOnClickListener {
            filterList("pariwisata")
        }
        binding.potensiCat3.setOnClickListener {
            filterList("sejarah")
        }
        binding.potensiAll.setOnClickListener {
            getPotensiList()
        }

    }

    private fun getPotensiList() {
        lifecycleScope.launch(Dispatchers.IO){
            val res = PotensiApi.getInstance().create(PotensiApiEndPoint::class.java).getPotensiData()
            withContext(Dispatchers.Main){
                binding.potensiRecycler.adapter = PotensiAdapter(requireContext(), res.body()!!.data)
            }

            Log.d("SHUBH", "getPotensiList: ${res.body()!!.data}")
        }
    }
    fun filterList(category: String = ""){
        Toast.makeText(activity, category, Toast.LENGTH_SHORT).show()
        lifecycleScope.launch(Dispatchers.IO){
            val res = PotensiApi.getInstance().create(PotensiApiEndPoint::class.java).getPotensiData()
            val listDataPotensi = res.body()!!.data
            val filteredPotensi = listDataPotensi.filter { it.kategori?.lowercase()?. contains(category)?:false }.toMutableList()
            withContext(Dispatchers.Main){
                binding.potensiRecycler.adapter = PotensiAdapter(requireContext(), filteredPotensi)
            }
            Log.d("SHUBH", "getPotensiList: ${res.body()!!.data}")
        }
    }

    /*
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }*/

}


