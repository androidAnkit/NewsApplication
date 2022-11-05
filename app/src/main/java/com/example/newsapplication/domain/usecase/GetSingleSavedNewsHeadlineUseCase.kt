package com.example.newsapplication.domain.usecase

import com.example.newsapplication.data.model.Article
import com.example.newsapplication.domain.repository.NewsRepository

class GetSingleSavedNewsHeadlineUseCase(private val newsRepository: NewsRepository) {
    suspend fun execute(articleUrl: String):String =
        newsRepository.getSingleSavedNews(articleUrl)
}