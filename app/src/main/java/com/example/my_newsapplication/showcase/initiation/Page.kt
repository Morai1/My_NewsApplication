package com.example.my_newsapplication.showcase.initiation

import android.icu.text.CaseMap.Title
import androidx.annotation.DrawableRes
import com.example.my_newsapplication.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)


val pages = listOf(
    Page(
        title = "Welcome to a World of News Application ",
        description = "Our app is a straightforward global news platform, " +
                "providing you with updates from around the world.",
        image = R.drawable.initiation1
    ),
    Page(
    title = "WorldNews360: Your Global News",
    description = "Discover a world of News at your fingertips with WorldNews360 application. " +
            "Explore a diverse array of global stories in one app.",
    image = R.drawable.initiation2
)
)



