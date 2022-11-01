package com.example.newsapplication.data.repository.dataSourceImpl

import com.example.newsapplication.data.api.NewsApiService
import com.example.newsapplication.data.model.APIResponse
import com.example.newsapplication.data.repository.dataSource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private val newsApiService: NewsApiService
) : NewsRemoteDataSource {
    override suspend fun getTopHeadlines(country: String, page: Int): Response<APIResponse> =
        newsApiService.getTopHeadlines(country, page)
}