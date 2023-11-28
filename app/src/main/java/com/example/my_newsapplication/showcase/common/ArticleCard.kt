package com.example.my_newsapplication.showcase.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.my_newsapplication.R
import com.example.my_newsapplication.domain.model.Article
import com.example.my_newsapplication.domain.model.Source
import com.example.my_newsapplication.showcase.Distance.ArticleCardSize
import com.example.my_newsapplication.showcase.Distance.ExtraSmallPadding
import com.example.my_newsapplication.showcase.Distance.ExtraSmallPadding2
import com.example.my_newsapplication.showcase.Distance.SmallIconSize
import com.example.my_newsapplication.ui.theme.My_NewsApplicationTheme

@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    article: Article,
    onClick:() -> Unit
) {

    val context = LocalContext.current

    Row(modifier = modifier.clickable{onClick()}) {
        
        AsyncImage(
            modifier = Modifier
                .size(ArticleCardSize)
                .clip(MaterialTheme.shapes.medium),
            model = ImageRequest.Builder(context).data(article.urlToImage).build(),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Column(verticalArrangement = Arrangement.SpaceAround, modifier = Modifier
            .padding(horizontal = ExtraSmallPadding)
            .height(
                ArticleCardSize
            )
        ) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(
                id = R.color.text_title
            ),

             maxLines = 2,
             overflow = TextOverflow.Ellipsis

            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = article.source.name,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(id = R.color.body)
                )

                Spacer(modifier = Modifier.width(ExtraSmallPadding2))
                Icon(painter = painterResource(id = R.drawable.ic_baseline_access_time_24), contentDescription = null,
                    modifier = Modifier.size(SmallIconSize),
                    tint = colorResource(id = R.color.body)
                    )
                
                Spacer(modifier = Modifier.width(ExtraSmallPadding2))
                Text(
                    text = article.publishedAt,
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(id = R.color.body)
                )

            }
            

        }
    }

}

@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ArticleCardPreview() {
    My_NewsApplicationTheme {
        Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            ArticleCard(
                article = Article(
                    author = "",
                    content = "",
                    description = "",
                    publishedAt = "1 hour",
                    source = Source(id = "", name = "Al Jazeera English"),
                    title = "Israel is out of Palestine. And now it's a free land",
                    url = "",
                    urlToImage = ""
                )
            ) {

            }
        }

    }
}