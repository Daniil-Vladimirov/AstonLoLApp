package com.example.astonlolapp.presentation.screens.favourite_heroes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.annotation.ExperimentalCoilApi
import com.example.astonlolapp.presentation.screens.favourite_heroes.compose.FavouriteHeroScreenCompose
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
                    favouriteHeroViewModel = favouriteHeroesViewModel,
                    onSwipeToDelete = {
                        favouriteHeroesViewModel.deleteFavouriteHero(it)
                    }
                )
            }
        }
    }
}