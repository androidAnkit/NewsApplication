package com.example.newsapplication.presentation.di

import android.app.Application
import androidx.room.Room
import com.example.newsapplication.data.dao.ArticleDao
import com.example.newsapplication.data.db.ArticleDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun newsDatabaseProvider(app:Application):ArticleDatabase =
        Room.databaseBuilder(app,
            ArticleDatabase::class.java,
            "ArticleDatabase")
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun newsDao(articleDatabase: ArticleDatabase):ArticleDao =
        articleDatabase.getArticleDao()
}