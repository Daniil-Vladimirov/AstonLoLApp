package com.example.astonlolapp.data.work_manager

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.astonlolapp.data.remote.HeroApi
import com.example.astonlolapp.domain.use_cases.UseCases
import com.example.astonlolapp.util.makeStatusNotification
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class HeroWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    val useCases: UseCases,
    val heroApi: HeroApi
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        makeStatusNotification("Fetching heroes", applicationContext)

        return try {
            heroApi.getAllHeroes()
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }


    companion object {
        const val WORK_RESULT = "work_result"
    }

}
