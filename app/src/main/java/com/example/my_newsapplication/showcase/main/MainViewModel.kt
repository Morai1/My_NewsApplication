package com.example.my_newsapplication.showcase.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.my_newsapplication.domain.usecases.news.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val newsUseCases: NewsUseCase
): ViewModel() {

    val news = newsUseCases.getNews(
    sources = listOf("abc-news", "al-jazeera-english", "google-news", "bbc-news", "google-news-sa")
    ).cachedIn(viewModelScope)
}