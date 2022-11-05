package com.example.newsapplication.domain.repository

import com.example.newsapplication.data.model.APIResponse
import com.example.newsapplication.data.model.Article
import com.example.newsapplication.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNewsHeadlines(country: String, page: Int): Resource<APIResponse>
    suspend fun getSearchNews(country: String, searchQuery: String, page: Int): Resource<APIResponse>
    suspend fun saveNews(article: Article):Long
    suspend fun deleteNews(article: Article)
    suspend fun getSavedNews(): Flow<List<Article>>
    suspend fun getSingleSavedNews(articleUrl: String):String
}