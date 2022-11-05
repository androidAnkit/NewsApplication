package com.example.newsapplication.presentation.di

import com.example.newsapplication.data.dao.ArticleDao
import com.example.newsapplication.data.repository.dataSource.NewsLocalDataSource
import com.example.newsapplication.data.repository.dataSourceImpl.NewsLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {
    @Singleton
    @Provides
    fun newsLocalDataSourceProvider(articleDao: ArticleDao):NewsLocalDataSource =
        NewsLocalDataSourceImpl(articleDao)
}