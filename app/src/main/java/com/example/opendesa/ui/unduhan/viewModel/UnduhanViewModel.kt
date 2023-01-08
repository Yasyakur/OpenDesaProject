package com.example.opendesa.ui.unduhan.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.opendesa.model.UnduhanModel
import com.example.opendesa.repository.Repository
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Response

class UnduhanViewModel(val repository: Repository) : ViewModel() {

    private val _onGetUnduhanData = MutableLiveData<UnduhanModel>()
    val onGetUnduhanData: LiveData<UnduhanModel>
        get() = _onGetUnduhanData

    private val _onGetErrorMessage = MutableLiveData<String>()
    val onGetErrorMessage: LiveData<String>
        get() = _onGetErrorMessage

    private val _loadingState = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean>
        get() = _loadingState

    fun getUnduhanData(type: String) {
        setLoading(true)
        viewModelScope.launch {
            val response = repository.getUnduhanData(type)
            if (response.isSuccessful) {
                setLoading(false)
                _onGetUnduhanData.value = response.body()
            } else {
                setLoading(false)
                _onGetErrorMessage.value = response.message()
            }
        }
    }

    private fun setLoading(show: Boolean) {
        _loadingState.value = show
    }
}