package com.example.newsapplication.data.repository.dataSource

import com.example.newsapplication.data.model.APIResponse
import retrofit2.Response

interface NewsRemoteDataSource {
    suspend fun getTopHeadlines(country: String, page: Int):Response<APIResponse>
}