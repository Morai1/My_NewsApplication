package com.example.my_newsapplication.showcase.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.my_newsapplication.domain.usecases.news.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase

): ViewModel(){

    private val _state = mutableStateOf(SearchState())
    val state: State<SearchState> = _state //how we make it mutable instead of the one below


    fun onEvent(event: SearchEvent){
        when(event){
            is SearchEvent.UpdateTheSearchQuery ->{
                _state.value = state.value.copy(searchQuery = event.searchQuery)

            }
            is SearchEvent.SearchArticles ->{
                searchArticles()

            }
        }
    }

    private fun searchArticles() {
        val articles = newsUseCase.searchArticles(
            searchQuery = state.value.searchQuery,
            sources =listOf("abc-news", "al-jazeera-english", "google-news", "bbc-news", "google-news-sa")
        ).cachedIn(viewModelScope)
        _state.value = state.value.copy(articles = articles )
    }
}