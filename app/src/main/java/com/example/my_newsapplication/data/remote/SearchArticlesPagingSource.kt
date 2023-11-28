package com.example.my_newsapplication.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.my_newsapplication.domain.model.Article

class SearchArticlesPagingSource (
    private val theNewsApi: TheNewsApi,
    private val searchQuery: String,
    private val sources: String
): PagingSource<Int, Article>() {

    private var totalNewsCount = 0


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1 //if it's not then start from page 1.
        return try {
            val newsResponse = theNewsApi.searchArticles(searchQuery = searchQuery,sources = sources, page = page)
            totalNewsCount += newsResponse.articles.size
            val articles =
                newsResponse.articles.distinctBy { it.title } //to filter our articles and remove any duplicates we have in this list
            LoadResult.Page(
                data = articles,
                nextKey = if (totalNewsCount == newsResponse.totalResults) null else page + 1,
                prevKey = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    }
}