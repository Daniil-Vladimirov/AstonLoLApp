package com.example.astonlolapp.domain.model

import android.app.Activity
import com.example.astonlolapp.MainActivity
import com.example.astonlolapp.presentation.screens.login_screen.LoginActivity
import com.example.astonlolapp.presentation.screens.onboarding_screen.OnBoardingActivity

data class ScreenState(
    val onBoardingState: Boolean,
    val loginState: Boolean
)

fun ScreenState.readStateForActivity(): Class<out Activity> {
    return if (!this.onBoardingState) OnBoardingActivity::class.java else {
        if (this.loginState) MainActivity::class.java else LoginActivity::class.java
    }
}
