package com.example.newsapplication.presentation.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapplication.domain.usecase.GetNewsHeadlinesUsecase
import com.example.newsapplication.domain.usecase.SearchNewsHeadlinesUsecase

class NewsViewModelFactory(
        private val app: Application,
        private val getNewsHeadlinesUsecase: GetNewsHeadlinesUsecase,
        private val getSearchNewsHeadlinesUsecase: SearchNewsHeadlinesUsecase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewsViewModel(app, getNewsHeadlinesUsecase, getSearchNewsHeadlinesUsecase) as T
    }
}