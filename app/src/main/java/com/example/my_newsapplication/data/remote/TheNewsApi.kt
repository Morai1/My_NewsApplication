package com.example.my_newsapplication.data.remote

import com.example.my_newsapplication.data.remote.dataTransferObject.NewsResponse
import com.example.my_newsapplication.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface TheNewsApi {

    @GET("everything")
    suspend fun getNews(
        //Since we are going to use paging 3 library
       // and we can pass a parameter query call this page, so we just know this from the documentation of this Api
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse


    @GET("everything")
    suspend fun searchArticles(
        @Query("q") searchQuery: String,
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse

}