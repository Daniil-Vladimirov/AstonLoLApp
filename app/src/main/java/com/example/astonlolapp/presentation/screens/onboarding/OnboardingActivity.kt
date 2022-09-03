package com.example.astonlolapp.presentation.screens.onboarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.astonlolapp.R
import com.example.astonlolapp.databinding.OnboardingViewpagerBinding

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: OnboardingViewpagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = OnboardingViewpagerBinding.inflate(layoutInflater)


        setContentView(binding.root)
        val viewPagerHeader = findViewById<ViewpagerHeader>(R.id.motionLayout)

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addPage("Page 1", R.layout.onboarding_1)
        adapter.addPage("Page 2", R.layout.onboarding_2)
        adapter.addPage("Page 3", R.layout.onboarding_3)
        binding.pager.adapter = adapter
            binding.tabs.setupWithViewPager(binding.pager)
        if (viewPagerHeader != null) {
            binding.pager.addOnPageChangeListener(viewPagerHeader)
        }


    }
}