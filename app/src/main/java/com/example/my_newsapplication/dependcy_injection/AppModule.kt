package com.example.my_newsapplication.dependcy_injection

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.my_newsapplication.data.local_database.NewsDataAccessObject
import com.example.my_newsapplication.data.local_database.NewsTypeConverter
import com.example.my_newsapplication.data.local_database.TheNewsDatabase
import com.example.my_newsapplication.data.management.LocalUserManagmentImpl
import com.example.my_newsapplication.data.remote.TheNewsApi
import com.example.my_newsapplication.data.repository.NewsRepositoryImplemantation
import com.example.my_newsapplication.domain.management.LocalUserManagment
import com.example.my_newsapplication.domain.repository.NewsRepository
import com.example.my_newsapplication.domain.usecases.application_entry.AppEntryUseCases
import com.example.my_newsapplication.domain.usecases.application_entry.RetrieveAppEntry
import com.example.my_newsapplication.domain.usecases.application_entry.StoreAppEntry
import com.example.my_newsapplication.domain.usecases.news.DeleteTheArticle
import com.example.my_newsapplication.domain.usecases.news.GetNews
import com.example.my_newsapplication.domain.usecases.news.NewsUseCase
import com.example.my_newsapplication.domain.usecases.news.SearchArticles
import com.example.my_newsapplication.domain.usecases.news.SelectArticle
import com.example.my_newsapplication.domain.usecases.news.SelectTheArticles
import com.example.my_newsapplication.domain.usecases.news.UpsertTheArticle
import com.example.my_newsapplication.util.Constants.BASE_URL
import com.example.my_newsapplication.util.Constants.THE_NEWS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

//to setup this module
// we need to annotate it with module which come from dragger
@Module
//make it Singletone so they will live as long
//as the application is a live
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManagement(
        application: Application
    ): LocalUserManagment = LocalUserManagmentImpl(application)



    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManagment: LocalUserManagment
    ) = AppEntryUseCases(
        retrieveAppEntry = RetrieveAppEntry(localUserManagment),
        storeAppEntry = StoreAppEntry(localUserManagment)
    )

    @Provides
    @Singleton
    fun provideTheNewsApi(): TheNewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TheNewsApi::class.java)
    }


    @Provides
    @Singleton
    fun provideNewsRepository(
        theNewsApi: TheNewsApi,
        newsDataAccessObject: NewsDataAccessObject
    ): NewsRepository = NewsRepositoryImplemantation(theNewsApi, newsDataAccessObject)


    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository,
        newsDataAccessObject: NewsDataAccessObject
    ): NewsUseCase {
        return NewsUseCase(
            getNews = GetNews(newsRepository),
            searchArticles = SearchArticles(newsRepository),
            selectTheArticles = SelectTheArticles(newsRepository),
            deleteTheArticle = DeleteTheArticle(newsRepository),
            upsertTheArticle = UpsertTheArticle(newsRepository),
            selectArticle = SelectArticle(newsRepository)
        )
    }

    @Provides
    @Singleton
    fun provideTheNewsDatabase(
        application: Application
    ):TheNewsDatabase{
        // to build this database with room we can say
        return Room.databaseBuilder(
            context = application,
            klass = TheNewsDatabase::class.java,
            name = THE_NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration()//this function uses for migration if we update something on your database, so room will just migrate that database for us
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDataAccessObject(
        theNewsDatabase: TheNewsDatabase
    ): NewsDataAccessObject = theNewsDatabase.newsDataAccessObject
}