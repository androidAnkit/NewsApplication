package com.example.newsapplication.domain.usecase

import com.example.newsapplication.data.model.APIResponse
import com.example.newsapplication.data.util.Resource
import com.example.newsapplication.domain.repository.NewsRepository

class GetNewsHeadlinesUsecase(private val newsRespository: NewsRepository) {
    suspend fun execute(country: String, page: Int): Resource<APIResponse> =
        newsRespository.getNewsHeadlines(country, page)
}