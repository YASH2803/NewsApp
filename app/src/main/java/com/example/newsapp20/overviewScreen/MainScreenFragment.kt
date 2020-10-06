package com.example.newsapp20.overviewScreen


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp20.databinding.MainScreenFragmentBinding

class MainScreenFragment : Fragment() {



    private val viewModel: MainScreenViewModel by lazy{
       ViewModelProvider(this).get(MainScreenViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = MainScreenFragmentBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
        return binding.root
    }

}