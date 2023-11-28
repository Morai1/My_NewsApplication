package com.example.my_newsapplication.data.local_database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.my_newsapplication.domain.model.Article

@Database(entities = [Article::class], version = 2)
@TypeConverters(NewsTypeConverter::class)
abstract class TheNewsDatabase: RoomDatabase() {

    abstract val newsDataAccessObject: NewsDataAccessObject
}