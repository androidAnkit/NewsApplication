package com.example.newsapplication.domain.usecase

import com.example.newsapplication.data.model.APIResponse
import com.example.newsapplication.data.util.Resource
import com.example.newsapplication.domain.repository.NewsRepository

class SearchNewsHeadlinesUsecase(private val newsRespository: NewsRepository) {
    suspend fun execute(searchQuery:String): Resource<APIResponse> =
        newsRespository.getSearchNews(searchQuery)
}