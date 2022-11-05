package com.example.newsapplication.presentation.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.example.newsapplication.presentation.activity.MainActivity
import com.example.newsapplication.R
import com.example.newsapplication.databinding.FragmentInfoBinding
import com.example.newsapplication.presentation.viewModel.NewsViewModel
import com.google.android.material.snackbar.Snackbar

class InfoFragment : Fragment() {

    private lateinit var fragmentInfoBinding: FragmentInfoBinding
    private lateinit var viewModel: NewsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentInfoBinding = FragmentInfoBinding.bind(view)
        val args: InfoFragmentArgs by navArgs()
        val article = args.selectedArticle
        viewModel = (activity as MainActivity).newsViewModel

        fragmentInfoBinding.wvInfo.apply {
            webViewClient = WebViewClient()
            if (article.url?.isNotEmpty() == true) {
                loadUrl(article.url.toString())
            }
        }

        fragmentInfoBinding.fabSave.setOnClickListener {
            viewModel.getSingleNewsArticle(article.url.toString()).observe(viewLifecycleOwner){
                if(it!=null){
                    if (article.url.toString() == it) {
                        Log.i("MYTAG", "Saved Successfully on first check")
                        Snackbar.make(view, "Already Saved!!", Snackbar.LENGTH_LONG).show()
                        return@observe
                    } else {
                        viewModel.saveNewsHeadlines(article).observe(viewLifecycleOwner){
                            if (it!= null) {
                                Log.i("MYTAG", "The response value is: ${it}")
                                Snackbar.make(view, "Saved Successfully!!", Snackbar.LENGTH_LONG).show()
                                if (it > 0)
                                    Log.i("MYTAG", "Saved Successfully")
                            }
                        }

                    }
                } else {
                    viewModel.saveNewsHeadlines(article).observe(viewLifecycleOwner){
                        if (it!= null) {
                            Log.i("MYTAG", "The response value is: ${it}")
                            Snackbar.make(view, "Saved Successfully!!", Snackbar.LENGTH_LONG).show()
                            if (it > 0)
                                Log.i("MYTAG", "Saved Successfully")
                        }
                    }
                }
            }
        }


    }
}