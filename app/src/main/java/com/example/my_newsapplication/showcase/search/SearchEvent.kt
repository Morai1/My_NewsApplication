package com.example.my_newsapplication.showcase.search

sealed class SearchEvent {

    data class UpdateTheSearchQuery(val searchQuery: String): SearchEvent()

    object SearchArticles: SearchEvent()
}