package com.example.newsapplication.data.repository.dataSourceImpl

import com.example.newsapplication.data.dao.ArticleDao
import com.example.newsapplication.data.model.Article
import com.example.newsapplication.data.repository.dataSource.NewsLocalDataSource
import kotlinx.coroutines.flow.Flow

class NewsLocalDataSourceImpl(
    private val articleDao: ArticleDao
): NewsLocalDataSource {
    override suspend fun saveNewsHeadlines(article: Article):Long =
        articleDao.insert(article)

    override fun getSavedNewsHeadlines(): Flow<List<Article>> =
        articleDao.getAllSavedNewsHeadlines()

    override suspend fun deleteSavedNewsHeadLines(article: Article) {
        articleDao.deleteSaveNesHeadlines(article)
    }

    override suspend fun getSingleSavedNewsArticle(articleUrl: String):String =
        articleDao.getSingleSaveNewsArticle(articleUrl)


}