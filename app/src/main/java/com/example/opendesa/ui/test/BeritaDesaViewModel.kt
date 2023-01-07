package com.example.opendesa.ui.test

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.opendesa.model.Berita
import com.example.opendesa.repository.Repository
import kotlinx.coroutines.launch

class BeritaDesaViewModel(private val repository: Repository) : ViewModel() {
    private val _beritaDesa = MutableLiveData<List<Berita>>()
    val beritaDesa: LiveData<List<Berita>> = _beritaDesa

    fun getBeritaDesaa() {
        viewModelScope.launch {
            val response: List<List<Berita>> = repository.getBerita()
            _beritaDesa.value = response[0]
        }
    }
}