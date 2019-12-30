package com.example.refrigerator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.refrigerator.data.RefrigeratorRepository

@Suppress("UNCHECKED_CAST")
class RefrigeratorViewModelFactory (
    private val repository: RefrigeratorRepository
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RefrigeratorViewModel(repository) as T
    }
}
