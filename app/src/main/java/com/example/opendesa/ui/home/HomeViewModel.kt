package com.example.opendesa.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.opendesa.model.Berita
import com.example.opendesa.repository.Repository
import kotlinx.coroutines.launch

enum class BeritaApiStatus { LOADING, ERROR, DONE }

class HomeViewModel(private val repository: Repository) : ViewModel() {
    private val _berita = MutableLiveData<List<Berita>>()
    val berita : LiveData<List<Berita>> = _berita

    private val _status = MutableLiveData<BeritaApiStatus>()
    val status : LiveData<BeritaApiStatus> = _status

    fun getBeritaKecamatan() {
        viewModelScope.launch {
            _status.value = BeritaApiStatus.LOADING
            try {
                val response: List<List<Berita>> = repository.getBerita()
                val listBerita = mutableListOf<Berita>()
                for (i in 0..2){
                    listBerita.add(response[0][i])
                }
                _berita.value = listBerita.toList()
                _status.value = BeritaApiStatus.DONE
            } catch (e: Exception) {
                _status.value = BeritaApiStatus.ERROR
                _berita.value = listOf()
            }
        }
    }
}