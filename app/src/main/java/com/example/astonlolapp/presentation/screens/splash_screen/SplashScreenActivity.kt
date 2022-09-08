package com.example.astonlolapp.presentation.screens.splash_screen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.lifecycleScope
import com.example.astonlolapp.MainActivity
import com.example.astonlolapp.R
import com.example.astonlolapp.databinding.ActivitySplashScreenBinding
import com.example.astonlolapp.presentation.screens.onboarding_screen.OnBoardingActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
@Suppress("DEPRECATION")
class SplashScreenActivity : AppCompatActivity() {

    private val splashViewModel by viewModels<SplashViewModel>()
    private var onBoardingState by Delegates.notNull<Boolean>()
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)




        // This is used to hide the status bar and make
        // the splash screen as a full screen activity.
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        // HERE WE ARE TAKING THE REFERENCE OF OUR IMAGE
        // SO THAT WE CAN PERFORM ANIMATION USING THAT IMAGE
        val backgroundImage: ImageView = findViewById(R.id.SplashScreenImage)
        val slideAnimation = AnimationUtils.loadAnimation(this, R.anim.top_slide)
        backgroundImage.startAnimation(slideAnimation)

        lifecycleScope.launch {
            splashViewModel.onBoardingCompleted.collectLatest {
                onBoardingState = it
                delay(1000)
                startActivityFor(
                    onBoardingState,
                    context = this@SplashScreenActivity,
                )
            }
        }
    }
}

fun startActivityFor(onBoardingState: Boolean, context: Context) {

    if (onBoardingState) {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(context, MainActivity::class.java)
            startActivity(context, intent, null)
            val activity = context as SplashScreenActivity
            activity.finish()
        }, 1000)
    } else {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(
                context,
                OnBoardingActivity::class.java
            ).addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            startActivity(context, intent, null)
            val activity = context as SplashScreenActivity
            activity.finish()
        }, 1000)
    }
}





