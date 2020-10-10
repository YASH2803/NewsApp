package com.example.newsapp20

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.newsapp20.model.News
import com.example.newsapp20.overviewScreen.NewsAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<News>?){
    val adapter = recyclerView.adapter as NewsAdapter
    adapter.submitList(data)
}

@BindingAdapter("imgUrl")
fun imgUrlToImg(imgView: ImageView, imgUrl: String?){

    imgUrl?.let{
        val imgUri = it.toUri().buildUpon().build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}

@BindingAdapter("formattedDate")
fun formatDate(txtView: TextView, stringDate: String?){
    val formattedDate = stringDate?.split("T")
    val date = formattedDate?.get(0)
    val time = formattedDate?.get(1)?.removeSuffix("Z")

    val correctFormat = "$date, $time"
    txtView.text = correctFormat
}

