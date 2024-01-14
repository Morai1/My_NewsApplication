package com.example.my_newsapplication.showcase.details

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.my_newsapplication.R
import com.example.my_newsapplication.domain.model.Article
import com.example.my_newsapplication.domain.model.Source
import com.example.my_newsapplication.showcase.Distance.AveragePadding1
import com.example.my_newsapplication.showcase.Distance.AveragePadding2
import com.example.my_newsapplication.showcase.Distance.TheArticleImageHeight
import com.example.my_newsapplication.showcase.details.elements.DetailsTopBar
import com.example.my_newsapplication.ui.theme.My_NewsApplicationTheme

@Composable
fun DetailsScreen(
    article: Article,
    event: (DetailsEvent) -> Unit,
    navigateUpward: () -> Unit

) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(
                top = AveragePadding1
            )
            .statusBarsPadding()
            .fillMaxSize()

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

        Spacer(modifier = Modifier.height(AveragePadding2))

        DetailsTopBar(
            onBrowsingClick = {
                Intent(Intent.ACTION_VIEW).also {
                    it.data = Uri.parse(article.url)
                    if (it.resolveActivity(context.packageManager) != null){
                        context.startActivity(it)
                    }
                }
            },
            onShareClick = {
                Intent(Intent.ACTION_SEND).also {
                    it.putExtra(Intent.EXTRA_TEXT, article.url)
                    it.type = "text/plain"
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onBookmarkClick = {event(DetailsEvent.UpsetDeleteTheArticle(article)) },
            onBackClick = navigateUpward
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = AveragePadding1,
                end = AveragePadding1,
                top = AveragePadding1
            )
        ){
            item {
                AsyncImage(
                    model = ImageRequest.Builder(context = context)
                        .data(article.urlToImage)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(TheArticleImageHeight)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(AveragePadding1))

                Text(
                    text = article.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = colorResource(
                        id = R.color.text_title
                    )

                )

                Text(
                    text = article.content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(
                        id = R.color.body
                    )
                )
            }

        }

    }
}


@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DetailsScreenPreview() {
    My_NewsApplicationTheme {
        Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            DetailsScreen(
                article = Article(
                    author = "",
                    title = "What is Gaza’s Indonesian hospital and why is Israel targeting it? ... - The Guardian ",
                    description = "Gaza health ministry says Israeli forces are ‘laying siege’ to hospital and fear repeat of events at al-Shifa ... - The Guardian",
                    content = "A week after Israeli forces stormed Gaza’s main hospital, al-Shifa, they have begun closing in on another, the Indonesian hospital, also in northern Gaza. The territory’s ministry of health said it believed 12 people had been killed in shelling overnight to Monday and that it feared a repeat of what happened at al-Shifa. (+1000 characters)",
                    publishedAt = "2023-11-21T12:51:20Z",
                    source = Source(id = "", name = "bbc"),
                    url = "https://www.theguardian.com/world/2023/nov/21/what-is-gaza-indonesian-hospital-why-israel-targeting-it",
                    urlToImage = "https://i.guim.co.uk/img/media/c120921816d327b1f365c6f2a93071716c9302bd/0_125_2936_1762/master/2936.jpg?width=700&dpr=2&s=none",

                    ),
                event = {}


            ) {

            }

        }

    }
}