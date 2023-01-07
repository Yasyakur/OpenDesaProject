package com.example.opendesa.ui.test

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.opendesa.databinding.FragmentBeritaDesaBinding
import com.example.opendesa.repository.Repository


class BeritaDesaFragment : Fragment() {

    private lateinit var beritaDesaViewModel: BeritaDesaViewModel
    private var _binding: FragmentBeritaDesaBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = Repository()
        val viewModelFactory = BeritaDesaViewModelFactory(repository)
        beritaDesaViewModel = ViewModelProvider(this, viewModelFactory)[BeritaDesaViewModel::class.java]
        beritaDesaViewModel.getBeritaDesaa()
        beritaDesaViewModel.beritaDesa.observe(this, Observer { response ->
            Log.d("RESPONSE", response[0].title)
            Log.d("RESPONSE", response[1].title)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBeritaDesaBinding.inflate(inflater, container, false)
        _binding!!.lifecycleOwner = this
        _binding!!.beritaDesaViewModel =  beritaDesaViewModel
        _binding!!.newsRecyclerView.adapter = BeritaDesaAdapter()
        val root: View = binding.root

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}