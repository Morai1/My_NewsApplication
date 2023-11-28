package com.example.my_newsapplication.data.local_database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.my_newsapplication.domain.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDataAccessObject {

    //If we trying to insert the same article we update the already existed article
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article)//act in 2 ways so it update and insert

    @Delete
    suspend fun delete(article: Article)

    //a function to retrieve the Articles
    @Query("SELECT * FROM ARTICLE")
    fun getArticles(): Flow<List<Article>>

    @Query("SELECT * FROM ARTICLE WHERE url=:url")
    suspend fun getArticle(url: String): Article?
}