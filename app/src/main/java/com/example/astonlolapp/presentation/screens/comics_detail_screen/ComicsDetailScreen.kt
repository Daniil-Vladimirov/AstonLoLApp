package com.example.astonlolapp.presentation.screens.comics_detail_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.astonlolapp.R
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalPagerApi::class)
@AndroidEntryPoint
class ComposeUIFragment : Fragment() {

    private lateinit var navController: NavController
    private val comicsDetailViewModel by viewModels<ComicsDetailViewModel>()

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val navHostFragment =
            parentFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController


        return ComposeView(requireContext()).apply {
            setContent {
                WelcomeScreen(
                    welcomeViewModel = comicsDetailViewModel,
                    navController = navController
                )
            }
        }
    }
}