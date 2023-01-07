package com.example.opendesa.ui.test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.opendesa.repository.Repository
import com.example.opendesa.ui.home.HomeViewModel

class BeritaDesaViewModelFactory (private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BeritaDesaViewModel(repository) as T
    }
}