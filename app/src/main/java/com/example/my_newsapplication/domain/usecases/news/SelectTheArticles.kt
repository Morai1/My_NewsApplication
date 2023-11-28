package com.example.my_newsapplication.domain.usecases.news

import com.example.my_newsapplication.data.local_database.NewsDataAccessObject
import com.example.my_newsapplication.domain.model.Article
import com.example.my_newsapplication.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectTheArticles(
    private val newsRepository: NewsRepository
){

    operator fun invoke(): Flow<List<Article>> {
        return newsRepository.selectArticles()
    }
}