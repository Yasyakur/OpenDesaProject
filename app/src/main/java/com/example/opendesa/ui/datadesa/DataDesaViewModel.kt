package com.example.opendesa.ui.datadesa

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class DataDesaViewModel : ViewModel(){
    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    private val _dataDesa = MutableLiveData<List<Desa>>()
    val dataDesa: LiveData<List<Desa>> = _dataDesa


    init {
        getDataDesa()
    }

    private fun getDataDesa() {
        viewModelScope.launch {
            try {
                val listResult = DataDesa.retrofitService.getDataDesa()
                _status.value = "Jumlah Data : ${listResult.size.toString()}"
                _dataDesa.value = listResult
                Log.d("Err", status.toString())

            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
                Log.d("Err", _status.value!!)
            }
        }

    }
}