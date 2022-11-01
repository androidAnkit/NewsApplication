package com.example.newsapplication.presentation.di

import com.example.newsapplication.data.api.NewsApiService
import com.example.newsapplication.data.repository.dataSource.NewsRemoteDataSource
import com.example.newsapplication.data.repository.dataSourceImpl.NewsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Provides
    fun newsRemoteDataSourceProvider(newsApiService: NewsApiService):NewsRemoteDataSource =
        NewsRemoteDataSourceImpl(newsApiService)
}
