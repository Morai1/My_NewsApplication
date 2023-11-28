package com.example.my_newsapplication.domain.usecases.news

data class NewsUseCase(
    val getNews: GetNews,
    val searchArticles: SearchArticles,
    val selectTheArticles: SelectTheArticles,
    val deleteTheArticle: DeleteTheArticle,
    val upsertTheArticle: UpsertTheArticle,
    val selectArticle: SelectArticle
)
