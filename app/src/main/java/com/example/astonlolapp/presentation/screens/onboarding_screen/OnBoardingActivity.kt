package com.example.astonlolapp.presentation.screens.onboarding_screen

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.astonlolapp.MainActivity
import com.example.astonlolapp.R
import com.example.astonlolapp.databinding.OnboardingViewpagerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingActivity : AppCompatActivity() {

    private lateinit var binding: OnboardingViewpagerBinding
    private val onBoardingViewModel by viewModels<OnBoardingViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = OnboardingViewpagerBinding.inflate(layoutInflater)



        setContentView(binding.root)

        val viewPagerHeader = findViewById<ViewpagerHeader>(R.id.motionLayout)


        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addPage("", R.layout.onboarding_1)
        adapter.addPage("", R.layout.onboarding_2)
        adapter.addPage("", R.layout.onboarding_3)
        binding.pager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.pager)
        if (viewPagerHeader != null) {
            binding.pager.addOnPageChangeListener(viewPagerHeader)
        }


    }

    fun onBoardingClicked(view: View) {
        onBoardingViewModel.saveOnBoardingState(state = true)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

    }

}

