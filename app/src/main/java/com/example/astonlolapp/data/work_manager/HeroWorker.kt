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
        makeStatusNotification("Fetching heroes", applicationContext)

        return try {
            val response = useCases.updateHeroesUseCase()
            Timber.d("$response")
            if (response) {
                Result.success()
            } else {
                Result.failure()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure()
        }
    }


    companion object {
        const val WORK_RESULT = "work_result"
    }

}
