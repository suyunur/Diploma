package com.example.diploma.ui.dashboard.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.diploma.data.model.News
import com.example.diploma.databinding.DiplomaFragmentMainPageBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private lateinit var binding: DiplomaFragmentMainPageBinding

    private val viewModel: HomeViewModel by viewModel()

    private lateinit var adapter: NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = DiplomaFragmentMainPageBinding.bind(view)

        setUpObservers()
    }

    init {
        viewModel.getNews()
    }

    private fun setUpObservers() {
        viewModel.newsLiveData.observe(viewLifecycleOwner, newsObserver)
    }

    private val newsObserver = Observer<List<News?>> {
        adapter = NewsAdapter(it)
        binding.newsRecyclerView.adapter = adapter
    }
}