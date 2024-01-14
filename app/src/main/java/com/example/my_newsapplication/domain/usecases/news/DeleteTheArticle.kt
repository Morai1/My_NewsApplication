package com.example.my_newsapplication.domain.usecases.news

import com.example.my_newsapplication.domain.model.Article
import com.example.my_newsapplication.domain.repository.NewsRepository

class DeleteTheArticle(
    private val newsRepository: NewsRepository
){

    suspend operator fun invoke(article: Article){
        newsRepository.deleteArticle(article)
    }
}