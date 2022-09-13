package com.example.astonlolapp.presentation.screens.favourite_heroes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.astonlolapp.ui.statusBarColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@ExperimentalCoilApi
@Composable
fun FavouriteHeroScreenCompose(
    navController: NavController,
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
            navController = navController
        )
    }

}