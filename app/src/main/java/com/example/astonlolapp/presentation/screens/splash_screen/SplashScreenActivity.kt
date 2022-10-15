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
import com.example.astonlolapp.R
import com.example.astonlolapp.databinding.ActivitySplashScreenBinding
import com.example.astonlolapp.domain.model.ScreenState
import com.example.astonlolapp.domain.model.readStateForActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
@Suppress("DEPRECATION")
class SplashScreenActivity : AppCompatActivity() {

    private val splashViewModel by viewModels<SplashViewModel>()
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        val backgroundImage: ImageView = findViewById(R.id.SplashScreenImage)
        val slideAnimation = AnimationUtils.loadAnimation(this, R.anim.top_slide)
        backgroundImage.startAnimation(slideAnimation)
        readScreenState()
    }

    private fun readScreenState() {
        lifecycleScope.launch {
            splashViewModel.screenState.collectLatest { screenState ->
                delay(1000)
                Timber.d("${screenState.onBoardingState}, ${screenState.loginState}")
                startActivityForLogin(this@SplashScreenActivity, screenState = screenState)
            }
        }
    }

    private fun startActivityForLogin(context: Context, screenState: ScreenState) {
        Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(context, screenState.readStateForActivity())
                startActivity(context, intent, null)
                val activity = context as SplashScreenActivity
                activity.finish()
            }, 1000)
        }

}






