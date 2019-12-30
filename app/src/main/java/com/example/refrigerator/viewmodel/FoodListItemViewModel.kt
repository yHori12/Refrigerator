
package com.example.refrigerator.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.refrigerator.data.Food
import com.example.refrigerator.data.FoodRepository
import kotlinx.coroutines.launch

class FoodListItemViewModel(
    val food:Food,
    private val foodRepository: FoodRepository
) : ViewModel() {

    fun addFoodToWaitingList() {
        viewModelScope.launch {
            foodRepository.addFood(food.copy(stockReserveCount = 1))
        }
    }
}
