package com.example.newsapplication.data.repository.dataSource

import com.example.newsapplication.data.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsLocalDataSource {
    suspend fun saveNewsHeadlines(article: Article):Long
    fun getSavedNewsHeadlines(): Flow<List<Article>>
    suspend fun deleteSavedNewsHeadLines(article: Article)
    suspend fun getSingleSavedNewsArticle(articleUrl: String):String
}
