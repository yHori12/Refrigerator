package com.example.refrigerator.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface FoodDao {
    @Query("SELECT * from Food ORDER BY name")
    fun getFoods(): LiveData<List<Food>>

    @Query("SELECT * from Food Where stockReserveCount > 0")
    fun getWaitingFoods(): LiveData<List<Food>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(plants: List<Food>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(plant: Food)

    @Query("SELECT * FROM Food WHERE id = :foodId")
    fun getFood(foodId: String): LiveData<Food>
}
