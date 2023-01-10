package com.example.opendesa.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.opendesa.repository.Repository
import com.example.opendesa.ui.unduhan.viewModel.UnduhanViewModel

class HomeViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(HomeViewModel::class.java)){
            HomeViewModel(repository) as T
        }else if (modelClass.isAssignableFrom(UnduhanViewModel::class.java)){
            UnduhanViewModel(repository) as T
        } else{
            HomeViewModel(repository) as T
        }
    }
}