package com.example.newsapplication.presentation.di

import com.example.newsapplication.domain.repository.NewsRepository
import com.example.newsapplication.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun getNewsUsecaseProvider(
        newsRepository: NewsRepository
    ):GetNewsHeadlinesUsecase =
        GetNewsHeadlinesUsecase(newsRepository)

    @Singleton
    @Provides
    fun getSavedNewsUsecaseProvider(
        newsRepository: NewsRepository
    ):GetSavedNewsHeadlinesUsecase =
        GetSavedNewsHeadlinesUsecase(newsRepository)

    @Singleton
    @Provides
    fun saveNewsUsecaseProvider(
        newsRepository: NewsRepository
    ):SaveNewsHeadlinesUsecase =
        SaveNewsHeadlinesUsecase(newsRepository)

    @Singleton
    @Provides
    fun deleteSavedNewsUsecaseProvider(
        newsRepository: NewsRepository
    ):DeleteSavedNewHeadlinesUsecase =
        DeleteSavedNewHeadlinesUsecase(newsRepository)

    @Singleton
    @Provides
    fun searchNewsUsecaseProvider(
        newsRepository: NewsRepository
    ):SearchNewsHeadlinesUsecase =
        SearchNewsHeadlinesUsecase(newsRepository)

}