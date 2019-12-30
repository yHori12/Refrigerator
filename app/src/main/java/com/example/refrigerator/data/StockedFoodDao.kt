package com.example.refrigerator.data

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * The Data Access Object for the Plant class.
 */
@Dao
interface StockedFoodDao {
    @Query("SELECT * from StockedFood")
    fun getStockedFood(): LiveData<List<StockedFood>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(plants: List<StockedFood>)

    @Insert
    suspend fun insertStockedFood(stockedFood: StockedFood)

    @Delete
    suspend fun deleteStockedFood(stockedFood: StockedFood)

}
