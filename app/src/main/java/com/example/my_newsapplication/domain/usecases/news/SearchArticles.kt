package com.example.my_newsapplication.domain.usecases.news

import androidx.paging.PagingData
import com.example.my_newsapplication.domain.model.Article
import com.example.my_newsapplication.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SearchArticles (
    private val newsRepository: NewsRepository
) {

    operator fun invoke(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.searchArticles(searchQuery = searchQuery, sources = sources)
    }

}