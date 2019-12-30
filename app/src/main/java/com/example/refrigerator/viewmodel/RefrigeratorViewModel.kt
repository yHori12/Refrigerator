package com.example.refrigerator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.refrigerator.data.Food
import com.example.refrigerator.data.RefrigeratorRepository
import com.example.refrigerator.data.StockedFood

class RefrigeratorViewModel internal constructor(
    refrigeratorRepository: RefrigeratorRepository
) : ViewModel() {
    val waitingFoods: LiveData<List<Food>> = refrigeratorRepository.getWaitingFood()

    val hasFood: LiveData<Boolean> = Transformations.map(waitingFoods) {
        !it.isNullOrEmpty()
    }
}
