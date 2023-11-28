package com.example.my_newsapplication.showcase.bookmark

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.my_newsapplication.R
import com.example.my_newsapplication.domain.model.Article
import com.example.my_newsapplication.showcase.Distance.AveragePadding1
import com.example.my_newsapplication.showcase.common.ArticlesList
import com.example.my_newsapplication.showcase.navigationgraph.Dispatch

@Composable
fun BookmarkScreen(
    state: BookmarkState,
    navigateToDetails: (Article) -> Unit

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(
                top = AveragePadding1,
                start = AveragePadding1,
                end = AveragePadding1
            )
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
        
        Text(
            text = "Bookmark",
            style = MaterialTheme.typography.displayMedium.copy(fontWeight = FontWeight.ExtraBold),
            color = colorResource(id = R.color.text_title)
        )
        Spacer(modifier = Modifier.height(AveragePadding1))
        
        ArticlesList(articles = state.article, onClick = {navigateToDetails(it)})



    }

}