package com.example.my_newsapplication.showcase.bookmark

import com.example.my_newsapplication.domain.model.Article

data class BookmarkState(
    val article: List<Article> = emptyList()
)
