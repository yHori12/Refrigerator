package com.example.refrigerator.viewmodel

import androidx.lifecycle.ViewModel
import com.example.refrigerator.data.FoodRepository

class FoodListViewModel internal constructor(foodRepository: FoodRepository):ViewModel(){

    val foods = foodRepository.getFoods()
}
