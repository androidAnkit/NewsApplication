package com.example.newsapplication.presentation.viewModel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.*
import com.example.newsapplication.data.model.APIResponse
import com.example.newsapplication.data.model.Article
import com.example.newsapplication.data.util.Resource
import com.example.newsapplication.domain.usecase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.Exception

class NewsViewModel
    (
    private val app: Application,
    private val getNewsHeadlinesUsecase: GetNewsHeadlinesUsecase,
    private val getSearchNewsHeadlinesUsecase: SearchNewsHeadlinesUsecase,
    private val saveNewsHeadlinesUsecase: SaveNewsHeadlinesUsecase,
    private val getSavedNewsHeadlinesUsecase: GetSavedNewsHeadlinesUsecase,
    private val deleteSavedNewHeadlinesUsecase: DeleteSavedNewHeadlinesUsecase,
    private val getSingleSavedNewsHeadlineUseCase: GetSingleSavedNewsHeadlineUseCase
) : AndroidViewModel(app) {

    // Get News Headlines
    val newsHeadlines: MutableLiveData<Resource<APIResponse>?> = MutableLiveData()
    fun getNewsHeadLines(country: String, page: Int) =
        viewModelScope.launch(Dispatchers.IO) {
            newsHeadlines.postValue(Resource.Loading())
            var apiResult: Resource<APIResponse>? = null
            try {
                if (isNetworkAvailable(app)) {
                    apiResult = getNewsHeadlinesUsecase.execute(country, page)
                    newsHeadlines.postValue(apiResult)
                } else {
                    newsHeadlines.postValue(Resource.Error("Internet is not available"))
                }
            } catch (e: Exception) {
                newsHeadlines.postValue(Resource.Error(e.message.toString(), null, apiResult?.code))
            }
        }

    // Get Searched News Headlines
    val searchedNews: MutableLiveData<Resource<APIResponse>?> = MutableLiveData()
    fun getSeachedNewsHeadLines(country: String, searchQuery: String, page: Int) =
        viewModelScope.launch {
            searchedNews.postValue(Resource.Loading())
            var apiResult: Resource<APIResponse>? = null
            try {
                if (isNetworkAvailable(app)) {
                    apiResult = getSearchNewsHeadlinesUsecase.execute(country, searchQuery, page)
                    searchedNews.postValue(apiResult)
                } else {
                    searchedNews.postValue(Resource.Error("No Internet connection available"))
                }
            } catch (e: Exception) {
                searchedNews.postValue(Resource.Error(e.message.toString(), null, apiResult?.code))
            }
        }

    fun saveNewsHeadlines(article: Article) =
        liveData {
            val response = saveNewsHeadlinesUsecase?.execute(article)
            emit(response)
        }

    fun getSingleNewsArticle(articleUrl: String) =
        liveData {
            val response = getSingleSavedNewsHeadlineUseCase.execute(articleUrl)
            emit(response)
        }

    fun getSavedNewsHeadlines() =
        liveData {
            getSavedNewsHeadlinesUsecase.execute().collect {
                emit(it)
            }
        }

    fun deleteSavedNewsHedline(article: Article) =
        viewModelScope.launch {
            deleteSavedNewHeadlinesUsecase.execute(article)
        }


    fun isNetworkAvailable(context: Context): Boolean {
        var result = false
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        cm?.run {
            cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                result = when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            }
        }
        return result
    }
}