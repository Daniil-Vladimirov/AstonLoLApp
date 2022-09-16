package com.example.astonlolapp.presentation.screens.favourite_heroes.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.astonlolapp.R
import com.example.astonlolapp.domain.model.Hero
import com.example.astonlolapp.presentation.screens.favourite_heroes.EmptyScreen
import com.example.astonlolapp.ui.*
import com.example.astonlolapp.util.Constants.BASE_URL
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@ExperimentalCoilApi
@Composable
fun ListContent(
    heroes: LazyPagingItems<Hero>,
    onSwipeToDelete: (Hero) -> Unit,
) {
    val result = handlePagingResult(heroes = heroes)

    if (result) {
        LazyColumn(
            contentPadding = PaddingValues(all = SMALL_PADDING),
            verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
        ) {
            items(
                items = heroes,
                key = { hero ->
                    hero.id
                }
            ) { hero ->
                val dismissState = rememberDismissState()
                val dismissDirection = dismissState.dismissDirection
                val isDismissed = dismissState.isDismissed(DismissDirection.EndToStart)
                if (isDismissed && dismissDirection == DismissDirection.EndToStart) {
                    val scope = rememberCoroutineScope()
                    SideEffect {
                        scope.launch {
                            delay(300)
                            if (hero != null) {
                                onSwipeToDelete(hero)
                            }
                        }
                    }
                }

                val degrees by animateFloatAsState(
                    if (dismissState.targetValue == DismissValue.Default)
                        0f
                    else
                        -45f
                )

                var itemAppeared by remember { mutableStateOf(false) }
                LaunchedEffect(key1 = true) {
                    itemAppeared = true
                }

                AnimatedVisibility(
                    visible = itemAppeared && !isDismissed,
                    enter = expandVertically(
                        animationSpec = tween(
                            durationMillis = 300
                        )
                    ),
                    exit = shrinkVertically(
                        animationSpec = tween(
                            durationMillis = 300
                        )
                    )
                ) {
                    SwipeToDismiss(
                        state = dismissState,
                        directions = setOf(DismissDirection.EndToStart),
                        dismissThresholds = { FractionalThreshold(fraction = 0.2f) },
                        background = { RedBackground(degrees = degrees) },
                        dismissContent = {
                            HeroItem(
                                hero = hero,
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun handlePagingResult(
    heroes: LazyPagingItems<Hero>
): Boolean {
    heroes.apply {
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }

        return when {
            loadState.refresh is LoadState.Loading -> {
                false
            }
            error != null -> {
                false
            }
            heroes.itemCount < 1 -> {
                EmptyScreen()
                false
            }
            else -> true
        }
    }
}

@ExperimentalCoilApi
@Composable
fun HeroItem(
    hero: Hero?,
) {
    Box(
        modifier = Modifier
            .height(HERO_ITEM_HEIGHT),
        contentAlignment = Alignment.BottomStart
    ) {
        Surface(shape = RoundedCornerShape(size = LARGE_PADDING)) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data = "$BASE_URL${hero?.image}")
                    .placeholder(R.drawable.ic_error_placeholder)
                    .error(R.drawable.ic_error_placeholder)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = stringResource(R.string.hero_image)
            )
        }
        Surface(
            modifier = Modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth(),
            color = Color.Black.copy(alpha = ContentAlpha.medium),
            shape = RoundedCornerShape(
                bottomStart = LARGE_PADDING,
                bottomEnd = LARGE_PADDING
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all = MEDIUM_PADDING),
                verticalArrangement = Arrangement.Center
            ) {
                hero?.name?.let {
                    Text(
                        text = it,
                        color = MaterialTheme.colors.topAppBarContentColor,
                        fontSize = MaterialTheme.typography.h5.fontSize,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}


@Composable
fun RedBackground(degrees: Float) {
    Surface(shape = RoundedCornerShape(LARGE_PADDING)) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(DeleteItem)
                .padding(horizontal = LARGE_PADDING),
            contentAlignment = Alignment.CenterEnd,
        ) {
            Icon(
                modifier = Modifier.rotate(degrees = degrees),
                imageVector = Icons.Filled.Delete,
                contentDescription = stringResource(R.string.delete_icon),
                tint = Color.White
            )
        }
    }

}

@OptIn(ExperimentalCoilApi::class)
@Composable
@Preview
fun HeroItemPreview() {
    HeroItem(
        hero = Hero(
            id = 1,
            name = "Aatrox",
            image = "/images/Aatrox_0.jpg",
            about = "",
            winRate = 47.0,
            role = "Top",
            ad = 34,
            ap = 23,
            hp = 345,
            mp = 134,
            range = false,
            abilities = listOf()
        )
    )
}