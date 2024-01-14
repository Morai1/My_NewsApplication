package com.example.my_newsapplication.showcase.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.my_newsapplication.R
import com.example.my_newsapplication.ui.theme.My_NewsApplicationTheme

fun Modifier.shimmerEffect() = composed {
    val transition = rememberInfiniteTransition(label = "")
    val alpha = transition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500),
            repeatMode = RepeatMode.Reverse

    ), label = ""
    ).value
    background(color = colorResource(id = R.color.shimmer).copy(alpha = alpha))
}

@Composable
fun ArticleCardsShimmerEffect(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .shimmerEffect()
    ) {

        // Shimmer for the image part of the ArticleCard
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.78f)
                .shimmerEffect(),
        )

        // Shimmer for the title text of the ArticleCard
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
                .padding(16.dp)
                .shimmerEffect()
        )
        Spacer(modifier = Modifier.height(8.dp)) // Space between title and subtitle

        // Shimmer for the subtitle text (source and date) of the ArticleCard
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                .shimmerEffect(),
        )
    }
}


@Preview(showBackground = true)
@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true)

@Composable
fun ArticleCardsShimmerEffectPreview() {
    My_NewsApplicationTheme {
        Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            ArticleCardsShimmerEffect()
        }
    }
}
