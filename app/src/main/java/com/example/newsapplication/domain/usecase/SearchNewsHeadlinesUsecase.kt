package com.example.newsapplication.domain.usecase

import com.example.newsapplication.data.model.APIResponse
import com.example.newsapplication.data.util.Resource
import com.example.newsapplication.domain.repository.NewsRepository

class SearchNewsHeadlinesUsecase(private val newsRespository: NewsRepository) {
    suspend fun execute(country: String, searchQuery:String, page: Int): Resource<APIResponse> =
        newsRespository.getSearchNews(country, searchQuery, page)
}