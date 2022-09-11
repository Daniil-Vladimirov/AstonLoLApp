package com.example.astonlolapp.domain.model

sealed class ComicsPage(

    var image: String,
    val title: String,
    val description: String
) {
    class First(image: String) : ComicsPage(
        image = image,
        title = "Greetings",
        description = "Are you a Boruto fan? Because if you are then we have a great news for you!"
    )

    class Second(image: String) : ComicsPage(
        image = image,
        title = "Explore",
        description = "Find your favorite heroes and learn some of the things that you didn't know about."
    )

    class Third(image: String) : ComicsPage(
        image = image,
        title = "Power",
        description = "Check out your hero's power and  see how much are they strong comparing to others."
    )
}