package com.example.newsapplication.presentation.di

import com.example.newsapplication.presentation.adapter.NewsAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Singleton
    @Provides
    fun newsAdapterProvider(): NewsAdapter =
        NewsAdapter()
}