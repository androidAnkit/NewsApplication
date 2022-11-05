package com.example.newsapplication.domain.repository

import com.example.newsapplication.data.model.APIResponse
import com.example.newsapplication.data.model.Article
import com.example.newsapplication.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNewsHeadlines(country: String, page: Int): Resource<APIResponse>
    suspend fun getSearchNews(country: String, page: Int, searchQuery: String): Resource<APIResponse>
    suspend fun saveNews(article: Article)
    suspend fun deleteNews(article: Article)
    suspend fun getSavedNews(): Flow<List<Article>>
}