package com.example.refrigerator.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.refrigerator.utilities.getValue
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FoodDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var foodDao: FoodDao
    private val foodA = Food("1", "A", "", 0, "")
    private val foodB = Food("2", "B", "", 0, "")
    private val foodC = Food("3", "C", "", 0, "")

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        foodDao = database.foodDao()

        // Insert foods in non-alphabetical order to test that results are sorted by name
        foodDao.insertAll(listOf(foodB, foodC, foodA))
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testGetfoods() {
        val foodList = getValue(foodDao.getFoods())
        Assert.assertThat(foodList.size, Matchers.equalTo(3))

        // Ensure food list is sorted by name
        Assert.assertThat(foodList[0], Matchers.equalTo(foodA))
        Assert.assertThat(foodList[1], Matchers.equalTo(foodB))
        Assert.assertThat(foodList[2], Matchers.equalTo(foodC))
    }

    @Test
    fun testGetfood() {
        Assert.assertThat(getValue(foodDao.getFood(foodA.foodId)), Matchers.equalTo(foodA))
    }

    @Test
    suspend fun testGetWaiting() {
        foodDao.insert(foodA.copy(stockReserveCount = 1))
        Assert.assertThat(
            getValue(foodDao.getWaitingFoods()).size,
            Matchers.equalTo(1)
        )
    }

    @Test
    suspend fun testInsert() {
        foodDao.insert(foodA.copy(stockReserveCount = 1))
        Assert.assertThat(
            getValue(foodDao.getFood(foodA.foodId)).stockReserveCount,
            Matchers.equalTo(1)
        )
    }

    @Test
    fun testGod() {
        fun twice(n: Int, f: (Int) -> Int) = f(f(n))
        val result = twice(5) { it * 2 }
        print(result)
        Assert.assertEquals(20, result)
    }
}
