package com.example.astonlolapp.domain.model

sealed class ComicsPage(

    var image: String,

) {
    class First(image: String) : ComicsPage(
        image = image,
    )

    class Second(image: String) : ComicsPage(
        image = image,
    )

    class Third(image: String) : ComicsPage(
        image = image,
    )
    class Forth(image: String) : ComicsPage(
        image = image,
    )
    class Fifth(image: String) : ComicsPage(
        image = image,
    )
    class Sixth(image: String) : ComicsPage(
        image = image,
    )
    class Seventh(image: String) : ComicsPage(
        image = image,
    )
    class Eighth(image: String) : ComicsPage(
        image = image,
    )
}