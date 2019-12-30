package com.example.refrigerator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.refrigerator.data.FoodRepository

class FoodListViewModelFactory(
    private val repository: FoodRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = FoodListViewModel(repository) as T
}
