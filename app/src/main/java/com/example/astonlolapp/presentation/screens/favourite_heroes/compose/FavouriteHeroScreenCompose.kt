package com.example.astonlolapp.presentation.screens.favourite_heroes.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.astonlolapp.domain.model.Hero
import com.example.astonlolapp.presentation.screens.favourite_heroes.FavouriteHeroesViewModel
import com.example.astonlolapp.ui.statusBarColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@ExperimentalCoilApi
@Composable
fun FavouriteHeroScreenCompose(
    onSwipeToDelete: (Hero) -> Unit,
    favouriteHeroViewModel: FavouriteHeroesViewModel
) {
    val allHeroes = favouriteHeroViewModel.getAllFavouriteHeroes.collectAsLazyPagingItems()

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colors.statusBarColor
    )

    Column(modifier = Modifier.fillMaxSize()) {
        ListContent(
            heroes = allHeroes,
            onSwipeToDelete = onSwipeToDelete
        )
    }

}