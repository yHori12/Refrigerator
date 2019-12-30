package com.example.refrigerator.data

class RefrigeratorRepository private constructor(
    private val stockedFoodDao: StockedFoodDao,
    private val foodDao: FoodDao
) {

    suspend fun createRefrigerator(foodId: String) {
        val stockedFood = StockedFood(foodId)
        stockedFoodDao.insertStockedFood(stockedFood)
    }

    fun getStockedFood() = stockedFoodDao.getStockedFood()

    fun getWaitingFood() = foodDao.getWaitingFoods()


    companion object {
        @Volatile
        private var instance: RefrigeratorRepository? = null

        fun getInstance(stockedFoodDao: StockedFoodDao,waitingFoodDao: FoodDao) =
            instance ?: synchronized(this) {
                instance ?: RefrigeratorRepository(stockedFoodDao,waitingFoodDao).also { instance = it }
            }
    }
}
