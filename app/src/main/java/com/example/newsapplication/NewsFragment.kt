package com.example.newsapplication

import android.os.Bundle
import android.util.Log
import android.view.AbsSavedState
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapplication.data.util.Resource
import com.example.newsapplication.databinding.FragmentNewsBinding
import com.example.newsapplication.presentation.adapter.NewsAdapter
import com.example.newsapplication.presentation.viewModel.NewsViewModel

class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding
    private lateinit var viewModel: NewsViewModel
    private lateinit var news_adapter: NewsAdapter
    private val country = "us"
    private var page = 1
    private var isScrolling = false
    private var isLoading = false
    private var isLastPage = false
    private var pages = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsBinding.bind(view)
        viewModel = (activity as MainActivity).newsViewModel
        news_adapter = (activity as MainActivity).newsAdapter
        initRecyclerView()
        viewNewsList()
    }

    private fun initRecyclerView() {
        binding.rvNews.apply {
            adapter = news_adapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@NewsFragment.onScrollListener)
        }
    }

    private fun showProgressBar(){
        isLoading = true
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar(){
        isLoading = false
        binding.progressBar.visibility = View.INVISIBLE
    }

    private fun viewNewsList(){
        viewModel.getNewsHeadLines(country, page)
        viewModel.newsHeadlines.observe(viewLifecycleOwner, {response ->
            when(response){
                is Resource.Success->{
                    hideProgressBar()
                    response.data?.let {
                        Log.i("MYTAG", "article size: ${it.articles.toList().size}")
                        news_adapter.differ.submitList(it.articles.toList())
                        if(it.totalResults%20==0)
                            pages = it.totalResults/20
                        else{
                            pages = it.totalResults/20+1
                        }
                        isLastPage = page == pages
                    }
                }

                is Resource.Loading->{
                    showProgressBar()
                }

                is Resource.Error->{
                    hideProgressBar()
                        Toast.makeText(activity, "An error occured: ${response.message}, code: ${response.code}", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private val onScrollListener = object : RecyclerView.OnScrollListener(){
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = binding.rvNews.layoutManager as LinearLayoutManager
            val sizeOfCurrentList = layoutManager.itemCount
            val visibleItems = layoutManager.childCount
            val topPosition = layoutManager.findFirstVisibleItemPosition()

            val hasReachedToEnd = topPosition + visibleItems >= sizeOfCurrentList
            val shouldPaginate = !isLoading && !isLastPage && hasReachedToEnd && isScrolling
            if(shouldPaginate){
                page++
                viewModel.getNewsHeadLines(country, page)
                isScrolling = false
            }

        }
    }
}