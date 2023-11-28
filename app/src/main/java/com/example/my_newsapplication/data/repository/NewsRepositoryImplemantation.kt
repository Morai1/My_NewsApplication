package com.example.my_newsapplication.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.my_newsapplication.data.local_database.NewsDataAccessObject
import com.example.my_newsapplication.data.remote.NewsPagingSource
import com.example.my_newsapplication.data.remote.SearchArticlesPagingSource
import com.example.my_newsapplication.data.remote.TheNewsApi
import com.example.my_newsapplication.domain.model.Article
import com.example.my_newsapplication.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

class NewsRepositoryImplemantation(
    private val newsApi: TheNewsApi,
    private val newsDataAccessObject: NewsDataAccessObject
): NewsRepository {

    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
               NewsPagingSource(
                   theNewsApi = newsApi,
                   sources = sources.joinToString(separator = ",")
               )
            }
        ).flow
    }

    override fun searchArticles(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchArticlesPagingSource(
                    searchQuery = searchQuery,
                    theNewsApi = newsApi,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override suspend fun upsertArticle(article: Article) {
        newsDataAccessObject.upsert(article)
    }

    override suspend fun deleteArticle(article: Article) {
        newsDataAccessObject.delete(article)
    }

    override fun selectArticles(): Flow<List<Article>> {
        return newsDataAccessObject.getArticles()
    }

    override suspend fun selectArticle(url: String): Article? {
        return newsDataAccessObject.getArticle(url = url)
    }

}
