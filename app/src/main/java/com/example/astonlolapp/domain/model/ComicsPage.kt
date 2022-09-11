package com.example.astonlolapp.domain.model

import androidx.annotation.DrawableRes
import com.example.astonlolapp.R

sealed class ComicsPage (
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
) {
    object First : ComicsPage(
        image = R.drawable.ic_comics,
        title = "Greetings",
        description = ""
    )

    object Second : ComicsPage(
        image = R.drawable.ic_hero_list,
        title = "Explore",
        description = ""
    )

    object Third : ComicsPage(
        image = R.drawable.ic_locations,
        title = "Power",
        description = ""
    )
}