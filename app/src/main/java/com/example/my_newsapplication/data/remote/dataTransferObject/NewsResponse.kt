package com.example.my_newsapplication.data.remote.dataTransferObject

import com.example.my_newsapplication.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)