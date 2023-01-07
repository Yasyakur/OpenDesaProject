package com.example.opendesa.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.opendesa.databinding.FragmentHomeBinding
import com.example.opendesa.repository.Repository

class HomeFragment : Fragment() {
    private lateinit var beritaViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = Repository()
        val viewModelFactory = HomeViewModelFactory(repository)
        beritaViewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
        beritaViewModel.getBeritaKecamatan()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        _binding!!.lifecycleOwner = this
        _binding!!.beritaViewModel = beritaViewModel
        _binding!!.beritaRecyclerView.adapter = BeritaAdapter()
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.submitIdKeluhan.setOnClickListener {
            getIdValue()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun getIdValue(){
        val keluhanId : String = binding.inputIdKeluhan.text.toString()
        Log.d("ID", keluhanId)
    }
}