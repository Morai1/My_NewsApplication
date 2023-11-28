package com.example.my_newsapplication.showcase.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.my_newsapplication.R
import com.example.my_newsapplication.domain.model.Article
import com.example.my_newsapplication.showcase.Distance.AveragePadding1
import com.example.my_newsapplication.showcase.common.ArticlesList
import com.example.my_newsapplication.showcase.common.SearchBar
import com.example.my_newsapplication.showcase.navigationgraph.Dispatch

@Composable
fun SearchScreen(
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigateToDetails: (Article) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(
                top = AveragePadding1,
                start = AveragePadding1,
                end = AveragePadding1
            )
            .statusBarsPadding()
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = null,
            modifier = Modifier
                .width(500.dp)
                .height(30.dp)
                .scale(8f)
                .padding(horizontal = AveragePadding1)

        )
        Spacer(modifier = Modifier.height(AveragePadding1))

        SearchBar(
            text = state.searchQuery,
            readOnly = false,
            onValueChange = { event(SearchEvent.UpdateTheSearchQuery(it)) },
            onSearch = { event(SearchEvent.SearchArticles)})

        Spacer(modifier = Modifier.height(AveragePadding1))
        state.articles?.let {
            val articles = it.collectAsLazyPagingItems()
            ArticlesList(articles = articles, onClick = { navigateToDetails(it) })
        }

    }

}