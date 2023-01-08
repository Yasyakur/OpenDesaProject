package com.example.opendesa.ui.beritadesa

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.opendesa.repository.Repository

class BeritaDesaViewModelFactory (private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BeritaDesaViewModel(repository) as T
    }
}