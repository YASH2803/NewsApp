package com.example.newsapp20.overviewScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp20.api.NewsApi
import com.example.newsapp20.model.NewsJsonResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainScreenViewModel : ViewModel() {
    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
    get() = _response

    private val viewModelJob = Job()
    val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    init{
        getNewsJsonService()
    }

    private fun getNewsJsonService() {
        coroutineScope.launch {
            var newsList = NewsApi.retrofitService.fetchNews()
            try {
                _response.value = "${newsList.totalResults}"
            }catch (e:Exception){
                _response.value = "${e.message}"
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}