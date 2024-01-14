package com.example.my_newsapplication.showcase.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.my_newsapplication.domain.model.Article
import com.example.my_newsapplication.domain.usecases.news.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class DetailsViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase
): ViewModel() {

    var sideEffect by mutableStateOf<String?>(null)
        private set

    fun onEvent(event: DetailsEvent){
        when(event){
            is DetailsEvent.UpsetDeleteTheArticle ->{
                viewModelScope.launch {
                    val article = newsUseCase.selectArticle(event.article.url)
                    if (article == null ){
                        upsertTheArticle(event.article)
                    }else{
                        deleteTheArticle(event.article)
                    }
                }
            }
            is DetailsEvent.RemoveSideEffect ->{
                sideEffect = null
            }
        }
    }

    private suspend fun deleteTheArticle(article: Article) {
        newsUseCase.deleteTheArticle(article = article)
        sideEffect = "The article is deleted"
    }

    private suspend fun upsertTheArticle(article: Article) {
        newsUseCase.upsertTheArticle(article = article)
        sideEffect = "The article is saved"
    }

}