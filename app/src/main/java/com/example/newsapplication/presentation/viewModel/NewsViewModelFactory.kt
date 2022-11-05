package com.example.newsapplication.presentation.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapplication.domain.usecase.*

class NewsViewModelFactory(
    private val app: Application,
    private val getNewsHeadlinesUsecase: GetNewsHeadlinesUsecase,
    private val getSearchNewsHeadlinesUsecase: SearchNewsHeadlinesUsecase,
    private val saveNewsHeadlinesUsecase: SaveNewsHeadlinesUsecase,
    private val getSavedNewsHeadlinesUsecase: GetSavedNewsHeadlinesUsecase,
    private val deleteSavedNewHeadlinesUsecase: DeleteSavedNewHeadlinesUsecase,
    private val getSingleSavedNewsHeadlineUseCase: GetSingleSavedNewsHeadlineUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(
            app,
            getNewsHeadlinesUsecase,
            getSearchNewsHeadlinesUsecase,
            saveNewsHeadlinesUsecase,
            getSavedNewsHeadlinesUsecase,
            deleteSavedNewHeadlinesUsecase,
            getSingleSavedNewsHeadlineUseCase
        ) as T
    }
}