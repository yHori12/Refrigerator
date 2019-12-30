package com.example.refrigerator.utilities

import android.content.Context
import com.example.refrigerator.data.AppDatabase
import com.example.refrigerator.data.FoodRepository
import com.example.refrigerator.data.RefrigeratorRepository
import com.example.refrigerator.viewmodel.FoodListViewModelFactory
import com.example.refrigerator.viewmodel.RefrigeratorViewModelFactory

/**
 * Static methods used to inject classes needed for various Activities and Fragments.
 */
object InjectorUtils {

    fun getPlantRepository(context: Context): FoodRepository {
        return FoodRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).foodDao()
        )
    }

    private fun getRefrigeratorRepository(context: Context): RefrigeratorRepository {
        return RefrigeratorRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).stockedFoodDao(),
            AppDatabase.getInstance(context.applicationContext).foodDao()
        )
    }

    fun provideRefrigeratorViewModelFactory(
        context: Context
    ): RefrigeratorViewModelFactory {
        val repository = getRefrigeratorRepository(context)
        return RefrigeratorViewModelFactory(repository)
    }

    fun providePlantListViewModelFactory(context: Context): FoodListViewModelFactory {
        val repository = getPlantRepository(context)
        return FoodListViewModelFactory(repository)
    }

}

