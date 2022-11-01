package com.example.newsapplication.data.api

import com.example.newsapplication.data.model.APIResponse
import com.example.newsapplication.BuildConfig
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country")
            country:String,
        @Query("page")
            page:Int,
        @Query("apiKey")
            apiKey:String = BuildConfig.API_KEY
    ): Response<APIResponse>
}