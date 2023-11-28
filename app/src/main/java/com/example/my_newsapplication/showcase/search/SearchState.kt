package com.example.my_newsapplication.showcase.search

import androidx.paging.PagingData
import com.example.my_newsapplication.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null
) {
}