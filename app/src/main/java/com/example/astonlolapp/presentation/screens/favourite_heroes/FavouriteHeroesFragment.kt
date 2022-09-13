package com.example.astonlolapp.presentation.screens.favourite_heroes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import coil.annotation.ExperimentalCoilApi
import dagger.hilt.android.AndroidEntryPoint

@OptIn(ExperimentalCoilApi::class)
@AndroidEntryPoint
class FavouriteHeroesFragment : Fragment() {


    private val favouriteHeroesViewModel by viewModels<FavouriteHeroesViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setContent {
                FavouriteHeroScreenCompose(
                    navController = findNavController(),
                    favouriteHeroViewModel = favouriteHeroesViewModel
                )

            }
        }
    }
}