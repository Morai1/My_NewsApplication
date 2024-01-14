package com.example.my_newsapplication.showcase.bookmark

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.my_newsapplication.R
import com.example.my_newsapplication.domain.model.Article
import com.example.my_newsapplication.showcase.Distance.AveragePadding1
import com.example.my_newsapplication.showcase.common.ArticlesList



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
        Text(
            text = "NEWS",
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            color = Color.Red,
            fontSize = 40.sp,
            modifier = Modifier
                .fillMaxWidth()

        )

        Spacer(modifier = Modifier.height(AveragePadding1))

        Text(
            text = "Bookmark",
            style = TextStyle(
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Bold,
                fontSize = 50.sp,
                letterSpacing = 0.15.sp,
                color = colorResource(id = R.color.text_title)

            )
        )


        Spacer(modifier = Modifier.height(AveragePadding1))

        ArticlesList(articles = state.article, onClick = {navigateToDetails(it)})



    }

}