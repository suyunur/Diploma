package com.example.diploma.ui.dashboard.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.diploma.data.model.News
import com.example.diploma.databinding.DiplomaFragmentMainPageBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private var _binding: DiplomaFragmentMainPageBinding? = null
    private val binding get() = _binding!!

      private val viewModel: HomeViewModel by viewModel()

    private lateinit var adapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DiplomaFragmentMainPageBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getNews()

        setUpObservers()
    }

    private fun setUpObservers() {
        viewModel.newsLiveData.observe(viewLifecycleOwner, newsObserver)
    }

    private val newsObserver = Observer<List<News?>> {
        adapter = NewsAdapter(it)
        binding.newsRecyclerView.adapter = adapter
    }
}