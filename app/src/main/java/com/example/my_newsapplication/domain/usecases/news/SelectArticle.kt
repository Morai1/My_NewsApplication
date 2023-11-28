package com.example.my_newsapplication.domain.usecases.news

import com.example.my_newsapplication.data.local_database.NewsDataAccessObject
import com.example.my_newsapplication.domain.model.Article
import com.example.my_newsapplication.domain.repository.NewsRepository

class SelectArticle(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(url: String): Article? {
        return newsRepository.selectArticle(url)
    }
}
