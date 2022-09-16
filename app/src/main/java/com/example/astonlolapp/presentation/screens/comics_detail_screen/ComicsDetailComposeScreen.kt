package com.example.astonlolapp.presentation.screens.comics_detail_screen

import android.content.Context
import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.astonlolapp.R
import com.example.astonlolapp.domain.model.ComicsPage
import com.example.astonlolapp.ui.*
import com.example.astonlolapp.util.Constants.BASE_URL
import com.example.astonlolapp.util.Constants.COMICS_PAGE_COUNT
import com.example.astonlolapp.util.Constants.LAST_ON_COMICS_PAGE
import com.google.accompanist.pager.*


@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun ComicsPageScreen(
    comicsPictures: List<String>?,
    navController: NavController
) {
    LaunchedEffect(key1 = true) {
        createPages(comicsPictures)
    }

    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.comicsDetailBackground)
    ) {
        HorizontalPager(
            modifier = Modifier.weight(10f),
            state = pagerState,
            count = COMICS_PAGE_COUNT,
            verticalAlignment = Alignment.Top
        ) { position ->
            PagerScreen(comicsPicture = comicsPictures?.get(position))
        }
        HorizontalPagerIndicator(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterHorizontally),
            pagerState = pagerState,
            activeColor = MaterialTheme.colors.activeIndicatorColor,
            inactiveColor = MaterialTheme.colors.inactiveIndicatorColor,
            indicatorWidth = PAGING_INDICATOR_WIDTH,
            spacing = PAGING_INDICATOR_SPACING,
            indicatorHeight = PAGING_INDICATOR_HEIGHT
        )
        FinishButton(
            modifier = Modifier.weight(1f),
            pagerState = pagerState
        ) {
            navController.popBackStack(R.id.fragmentComics, inclusive = true)
            navController.navigate(R.id.fragmentComics)
        }
    }
}


@Composable
fun PagerScreen(comicsPicture: String?) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,

        ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = ImageRequest.Builder(LocalContext.current)
                .data(data = "$BASE_URL${comicsPicture}")
                .placeholder(R.drawable.ic_error_placeholder)
                .error(R.drawable.ic_error_placeholder)
                .build(),
            contentScale = ContentScale.scaleImage(LocalContext.current),
            contentDescription = stringResource(R.string.comics_image)
        )
    }
}


@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun FinishButton(
    modifier: Modifier,
    pagerState: PagerState,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .padding(horizontal = EXTRA_LARGE_PADDING),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(
            modifier = Modifier.fillMaxWidth(),
            visible = pagerState.currentPage == LAST_ON_COMICS_PAGE
        ) {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.buttonBackgroundColor,
                    contentColor = Color.White
                )
            ) {
                Text(text = stringResource(R.string.Finish))
            }
        }
    }
}

private fun createPages(comicsPictures: List<String>?): List<ComicsPage> {
    val list = mutableListOf<ComicsPage>()
    if (comicsPictures != null) {
        list.add(ComicsPage.First(image = comicsPictures[0]))
        list.add(ComicsPage.Second(image = comicsPictures[1]))
        list.add(ComicsPage.Third(image = comicsPictures[2]))
        list.add(ComicsPage.Forth(image = comicsPictures[3]))
        list.add(ComicsPage.Fifth(image = comicsPictures[4]))
        list.add(ComicsPage.Sixth(image = comicsPictures[5]))
        list.add(ComicsPage.Seventh(image = comicsPictures[6]))
        list.add(ComicsPage.Eighth(image = comicsPictures[7]))
        list.add(ComicsPage.Ninth(image = comicsPictures[8]))
        list.add(ComicsPage.Tenth(image = comicsPictures[9]))

    }
    return list
}

private fun ContentScale.Companion.scaleImage(context: Context): ContentScale {
    val orientation = context.resources.configuration.orientation
    return if (orientation == Configuration.ORIENTATION_PORTRAIT) {
        Crop
    } else {
        FillWidth
    }
}
