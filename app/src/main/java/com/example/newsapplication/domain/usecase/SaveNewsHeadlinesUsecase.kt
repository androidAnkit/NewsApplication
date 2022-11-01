package com.example.newsapplication.domain.usecase

import com.example.newsapplication.data.model.Article
import com.example.newsapplication.domain.repository.NewsRepository

class SaveNewsHeadlinesUsecase(private val newsRespository: NewsRepository) {
    suspend fun execute(article: Article){
        newsRespository.saveNews(article)
    }
}