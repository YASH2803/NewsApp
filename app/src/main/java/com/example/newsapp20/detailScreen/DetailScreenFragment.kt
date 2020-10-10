package com.example.newsapp20.detailScreen

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp20.R
import com.example.newsapp20.databinding.DetailScreenFragmentBinding

class DetailScreenFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application
        val binding = DetailScreenFragmentBinding.inflate(inflater)
        binding.setLifecycleOwner(this)

        val newsItem = DetailScreenFragmentArgs.fromBundle(requireArguments()).selectedNewsItem

        val viewModelFactory = DetailScreenViewModeFactory(newsItem!!, application)

        binding.detailViewModel = ViewModelProvider(this, viewModelFactory).get(DetailScreenViewModel::class.java)
0
        binding.detailViewUrl.setOnClickListener {
            val intentToBrowser = Intent(ACTION_VIEW)
            intentToBrowser.data = Uri.parse(newsItem?.url)
            startActivity(intentToBrowser)
        }

        return binding.root
    }

}
