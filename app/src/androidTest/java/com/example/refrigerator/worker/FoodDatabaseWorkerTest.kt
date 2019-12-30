package com.example.refrigerator.worker

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.work.ListenableWorker
import androidx.work.WorkManager
import androidx.work.testing.TestListenableWorkerBuilder
import org.hamcrest.CoreMatchers.`is`
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class FoodDatabaseWorkerTest {
    private lateinit var context: Context
    private lateinit var workManager: WorkManager

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        workManager = WorkManager.getInstance(context)
    }

    @Test
    fun doWork() {
        // Get the ListenableWorker
        val worker = TestListenableWorkerBuilder<FoodDatabaseWorker>(context).build()

        // start the work synchronously
        val result = worker.startWork().get()

        assertThat(result, `is`(ListenableWorker.Result.success()))
    }
}
