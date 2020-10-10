package com.example.newsapp20.detailScreen

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp20.model.News

class DetailScreenViewModeFactory(private val news: News,
private val application: Application) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailScreenViewModel::class.java)){
            return DetailScreenViewModel(news, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}