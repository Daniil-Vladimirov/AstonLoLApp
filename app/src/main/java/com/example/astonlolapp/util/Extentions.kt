package com.example.astonlolapp.util

import androidx.work.*
import com.example.astonlolapp.data.work_manager.HeroWorker
import java.util.concurrent.TimeUnit

fun WorkManager.setUpWorkManager() {
    val constraints = Constraints.Builder()
        .setRequiresCharging(true)
        .setRequiredNetworkType(NetworkType.UNMETERED)
        .build()
    val periodicWork =
        PeriodicWorkRequest
            .Builder(
                HeroWorker::class.java,
                Constants.WORK_MANAGER_REPEAT_INTERVAL,
                TimeUnit.HOURS
            )
            .setConstraints(constraints)
            .setInitialDelay(Constants.WORK_MANAGER_REPEAT_INTERVAL, TimeUnit.HOURS)
            .setBackoffCriteria(
                BackoffPolicy.LINEAR,
                PeriodicWorkRequest.MIN_BACKOFF_MILLIS,
                TimeUnit.MILLISECONDS
            )
            .build()
    this.enqueueUniquePeriodicWork(
        "updateHeroes",
        ExistingPeriodicWorkPolicy.REPLACE,
        periodicWork
    )
}