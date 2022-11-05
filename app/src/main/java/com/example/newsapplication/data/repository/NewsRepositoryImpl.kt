package com.example.newsapplication.data.repository

import com.example.newsapplication.data.model.APIResponse
import com.example.newsapplication.data.model.Article
import com.example.newsapplication.data.repository.dataSource.NewsRemoteDataSource
import com.example.newsapplication.data.util.Resource
import com.example.newsapplication.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource
):NewsRepository {
    override suspend fun getNewsHeadlines(country: String, page: Int): Resource<APIResponse> =
        responseToResult(newsRemoteDataSource.getTopHeadlines(country, page))

    private fun responseToResult(response:Response<APIResponse>):Resource<APIResponse>{
        if(response.isSuccessful){
            response.body()?.let {result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message(), null, response.code())
    }

    override suspend fun getSearchNews(country: String, page: Int, searchQuery: String): Resource<APIResponse>  =
        responseToResult(newsRemoteDataSource.getSearchedTopHeadlines(country, page, searchQuery))

    override suspend fun saveNews(article: Article) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNews(article: Article) {
        TODO("Not yet implemented")
    }

    override suspend fun getSavedNews(): Flow<List<Article>> {
        TODO("Not yet implemented")
    }
}