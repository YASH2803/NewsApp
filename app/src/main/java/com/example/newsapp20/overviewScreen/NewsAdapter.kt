package com.example.newsapp20.overviewScreen

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp20.databinding.NewsItemLayoutBinding
import com.example.newsapp20.model.News

class NewsAdapter(val onClickListener: OnClickListener) : ListAdapter<News, NewsAdapter.ViewHolder>(DiffCallBack){

    //This will feed the viewModel in news_item_layout's the data which is should have
    inner class ViewHolder(private val binding: NewsItemLayoutBinding)
        : RecyclerView.ViewHolder(binding.root){
        fun bind(news: News){
            binding.newsArticle = news
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    companion object DiffCallBack: DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.title == newItem.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(NewsItemLayoutBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val rowPosition = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(rowPosition)
        }
        holder.bind(rowPosition)
    }
    class OnClickListener(val clickListener: (news: News) -> Unit){
        fun onClick(news: News) = clickListener(news)
    }
}