package com.example.newsapp20.overviewScreen

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp20.api.NewsApi
import com.example.newsapp20.model.News
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@Suppress("DEPRECATION")
class MainScreenViewModel() : ViewModel() {


    private val _checkConnection = MutableLiveData<Boolean>()

    val checkConnection: LiveData<Boolean>
        get() = _checkConnection

    private val _newsArticles = MutableLiveData<List<News>>()

    val newsArticles: LiveData<List<News>>
    get() = _newsArticles

    private val _navigateToNews = MutableLiveData<News>()

    val navigateToNews: LiveData<News>
        get() = _navigateToNews

    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    init{
//        if(verifyAvailableNetwork(context)) {
//            _checkConnection.value = true
            getNewsJsonService()
//        }else{
//            _checkConnection.value = false
//        }
    }

    private fun getNewsJsonService() {
        coroutineScope.launch {
            try {
                var newsList = NewsApi.retrofitService.fetchNews()
                _newsArticles.value = newsList.articles
            }catch (e:Exception){
                _newsArticles.value = ArrayList()
                Log.i("MainScreenViewModel", "${e.message}")

            }
        }
    }

    private fun verifyAvailableNetwork(context: Context):Boolean{
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }

    fun displayNewsDetailScreen(news: News){
        _navigateToNews.value = news
    }

    fun displayNewsDetailScreenComplete(){
        _navigateToNews.value = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}