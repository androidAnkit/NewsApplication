package com.example.newsapplication.presentation.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapplication.domain.usecase.GetNewsHeadlinesUsecase

class NewsViewModelFactory(
    private val app: Application,
    private val getNewsHeadlinesUsecase: GetNewsHeadlinesUsecase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(app, getNewsHeadlinesUsecase) as T
    }
}