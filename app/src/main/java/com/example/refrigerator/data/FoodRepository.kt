package com.example.refrigerator.data

class FoodRepository private constructor(private val foodDao: FoodDao) {

    fun getFoods() = foodDao.getFoods()
    suspend fun addFood(food:Food) = foodDao.insert(food)

    companion object {
        @Volatile
        private var instance: FoodRepository? = null

        fun getInstance(foodDao: FoodDao) =
            instance ?: synchronized(this) {
                instance
                    ?: FoodRepository(foodDao).also { instance = it }
            }
    }
}
