package com.example.my_newsapplication.showcase.details

import com.example.my_newsapplication.domain.model.Article

sealed class DetailsEvent {

    data class UpsetDeleteTheArticle(val article: Article) : DetailsEvent()
    object RemoveSideEffect : DetailsEvent()

}