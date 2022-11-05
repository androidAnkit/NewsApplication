package com.example.newsapplication.data.dao

import androidx.room.*
import com.example.newsapplication.data.model.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article):Long

    @Query("Select * from articles")
    fun getAllSavedNewsHeadlines(): Flow<List<Article>>

    @Delete
    suspend fun deleteSaveNesHeadlines(article: Article)

    @Query("Select url from articles where url=:articleUrl ")
    suspend fun getSingleSaveNewsArticle(articleUrl: String):String
}