package com.example.astonlolapp.data.work_manager

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.astonlolapp.domain.use_cases.UseCases
import com.example.astonlolapp.util.makeStatusNotification
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import timber.log.Timber

@HiltWorker
class HeroWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    val useCases: UseCases,
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {

        return try {
            val response = useCases.updateHeroesUseCase()
            Timber.d("$response")
            if (response) {
                makeStatusNotification("Fetching heroes", applicationContext)
                Result.success()
            } else {
                Result.retry()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure()
        }
    }


}
