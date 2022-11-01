package com.example.newsapplication.presentation.di

import android.app.Application
import com.example.newsapplication.domain.usecase.GetNewsHeadlinesUsecase
import com.example.newsapplication.presentation.viewModel.NewsViewModel
import com.example.newsapplication.presentation.viewModel.NewsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {
    @Singleton
    @Provides
    fun viewModelFactoryProvider(
        application: Application,
        getNewsHeadlinesUsecase: GetNewsHeadlinesUsecase
    ):NewsViewModelFactory =
        NewsViewModelFactory(application, getNewsHeadlinesUsecase)
}