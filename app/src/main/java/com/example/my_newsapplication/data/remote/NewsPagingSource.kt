package com.example.my_newsapplication.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.my_newsapplication.domain.model.Article
import com.example.my_newsapplication.util.Constants.API_KEY

class NewsPagingSource(
    private val theNewsApi: TheNewsApi,
    private val sources: String
): PagingSource<Int, Article>() {

    private var totalNewsCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        //Here we make our request to the Api and return that articles
        // so in here we want to just get the page, we have this parents object "params"
        //we can use that to get the page

        val page = params.key ?: 1 //if it's not then start from page 1.
        return try {
            val newsResponse = theNewsApi.getNews(sources = sources, page = page)
            totalNewsCount += newsResponse.articles.size
            val articles = newsResponse.articles.distinctBy { it.title } //to filter our articles and remove any duplicates we have in this list
            LoadResult.Page(
                data = articles,
                nextKey = if (totalNewsCount == newsResponse.totalResults) null else page + 1,
                prevKey = null
            )
        }catch (e:Exception){
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