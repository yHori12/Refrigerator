package com.example.refrigerator.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.refrigerator.data.AppDatabase
import com.example.refrigerator.data.Food
import com.example.refrigerator.utilities.FOOD_DATA_FILENAME
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.coroutineScope


class FoodDatabaseWorker(
    context: Context,
    workerParameters: WorkerParameters
) : CoroutineWorker(context,workerParameters) {

    //初回起動時に一度だけ動く
    override suspend fun doWork(): Result = coroutineScope {
        try {
            applicationContext.assets.open(FOOD_DATA_FILENAME).use { inputStream ->
                    val string = inputStream.bufferedReader().use { it.readText() }
                    val foodType = Types.newParameterizedType(List::class.java,Food::class.java)
                    val moshi = Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                    val listAdapter: JsonAdapter<List<Food>> = moshi.adapter(foodType)
                    val foodList = listAdapter.fromJson(string)!!
                    val database = AppDatabase.getInstance(applicationContext)
                    database.foodDao().insertAll(foodList)

                    Result.success()
            }
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }

    companion object {
        private val TAG = FoodDatabaseWorker::class.java.simpleName
    }
}
