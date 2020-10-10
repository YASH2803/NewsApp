package com.example.newsapp20.overviewScreen


import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp20.databinding.MainScreenFragmentBinding
import kotlinx.android.synthetic.main.main_screen_fragment.*

class MainScreenFragment : Fragment() {



    private val viewModel: MainScreenViewModel by lazy{
       ViewModelProvider(this).get(MainScreenViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        Handler().postDelayed({
//            progress_loader.visibility = View.VISIBLE
//        },  2000)

        val binding = MainScreenFragmentBinding.inflate(inflater)
        binding.viewModel = viewModel
        binding.setLifecycleOwner(this)
//        val noUse = MainScreenViewModel(requireActivity().applicationContext)

//        viewModel.checkConnection.observe(viewLifecycleOwner, Observer {
//            if(it){
//                noInternet.visibility = View.GONE
//            }else{
//                noInternet.visibility = View.VISIBLE
//            }
//        })
        binding.newsRecyclerview.adapter = NewsAdapter(NewsAdapter.OnClickListener{
            viewModel.displayNewsDetailScreen(it)
        })



        viewModel.navigateToNews.observe(viewLifecycleOwner, Observer {
            if(it != null){
                findNavController().navigate(MainScreenFragmentDirections.actionMainScreenFragmentToDetailScreenFragment(it))
                viewModel.displayNewsDetailScreenComplete()
            }
        })
        return binding.root
    }

}