package com.example.my_newsapplication.showcase.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.example.my_newsapplication.R
import com.example.my_newsapplication.domain.model.Article
import com.example.my_newsapplication.showcase.Distance.AveragePadding1
import com.example.my_newsapplication.showcase.common.SearchBar
import com.example.my_newsapplication.showcase.common.ArticlesList

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    articles: LazyPagingItems<Article>,
    navigateToSearch: () -> Unit,
    navigateToDetails: (Article) -> Unit

    ) {


    val titles by remember {
        derivedStateOf {
            if (articles.itemCount > 12) {
                articles.itemSnapshotList.items
                    .slice(IntRange(start = 0, endInclusive = 11))
                    .joinToString(separator = " \uD83C\uDF0D ") { it.title }
            } else {
                ""
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = AveragePadding1)
            .statusBarsPadding()
    ) {
        Text(
            text = "NEWS",
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            color = Color.Red,
            fontSize = 40.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = AveragePadding1)
        )

        Spacer(modifier = Modifier.height(10.dp))
        SearchBar(
            modifier = Modifier
                .padding(horizontal = AveragePadding1)
                .fillMaxWidth(),
            text = "",
            readOnly = true,
            onValueChange = {},
            onSearch = {},
            onClick = {
                navigateToSearch()
            }
        )


            Spacer(modifier = Modifier.height(AveragePadding1))
            Text(
                text = titles,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = AveragePadding1)
                    .basicMarquee(),
                fontSize = 14.sp,
                color = colorResource(id = R.color.text_title)
            )

            Spacer(modifier = Modifier.height(AveragePadding1))

            ArticlesList(
                modifier = Modifier.padding(horizontal = AveragePadding1),
                articles = articles,
                onClick = {
                    navigateToDetails(it)
                }
            )


        }


    }

