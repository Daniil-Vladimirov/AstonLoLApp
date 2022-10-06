package com.example.astonlolapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import androidx.work.*
import com.example.astonlolapp.data.work_manager.HeroWorker
import com.example.astonlolapp.databinding.ActivityMainBinding
import com.example.astonlolapp.util.Constants.WORK_MANAGER_REPEAT_INTERVAL
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val workManager = WorkManager.getInstance(application)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController


        val bottomNavigation = binding.bottomNavigationView
        setUpWorkManager(workManager = workManager)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.comics_detail_fragment) {

                bottomNavigation.visibility = View.GONE
            } else {

                bottomNavigation.visibility = View.VISIBLE
            }
        }
        bottomNavigation.setupWithNavController(navController)
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.listScreenFragment, R.id.fragmentFavouriteHeroes, R.id.fragmentComics)
        )


    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration)
    }

    private fun setUpWorkManager(workManager: WorkManager) {
        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .build()
        val periodicWork =
            PeriodicWorkRequest
                .Builder(HeroWorker::class.java, WORK_MANAGER_REPEAT_INTERVAL, TimeUnit.HOURS)
                .setConstraints(constraints)
                .setInitialDelay(WORK_MANAGER_REPEAT_INTERVAL, TimeUnit.HOURS)
                .setBackoffCriteria(
                    BackoffPolicy.LINEAR,
                    PeriodicWorkRequest.MIN_BACKOFF_MILLIS,
                    TimeUnit.MILLISECONDS
                )
                .build()
        workManager.enqueueUniquePeriodicWork(
            "updateHeroes",
            ExistingPeriodicWorkPolicy.REPLACE,
            periodicWork
        )
    }
}






