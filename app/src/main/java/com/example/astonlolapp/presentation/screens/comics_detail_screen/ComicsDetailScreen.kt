package com.example.astonlolapp.presentation.screens.comics_detail_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.astonlolapp.databinding.FragmentDetailMotionBinding
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalPagerApi::class)
@AndroidEntryPoint
class ComicsDetailScreen : Fragment() {

    private var _binding: FragmentDetailMotionBinding? = null
    private val binding get() = _binding!!

    private val comicsDetailViewModel by viewModels<ComicsDetailViewModel>()

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setContent {
                ComicsPageScreen(
                    comicsPictures = comicsDetailViewModel.selectedComics.collectAsState().value?.text,
                    findNavController()
                    )
                    //navController = navController
            }
        }
    }
}