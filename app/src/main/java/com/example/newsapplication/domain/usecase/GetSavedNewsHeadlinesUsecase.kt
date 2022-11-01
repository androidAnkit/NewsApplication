package com.example.newsapplication.domain.usecase

import com.example.newsapplication.data.model.Article
import com.example.newsapplication.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedNewsHeadlinesUsecase(private val newsRespository: NewsRepository) {
    suspend fun execute(): Flow<List<Article>> =
        newsRespository.getSavedNews()
}