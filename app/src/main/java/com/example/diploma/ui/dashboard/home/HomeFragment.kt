package com.example.diploma.ui.dashboard.home

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.diploma.R
import com.example.diploma.data.CAROUSEL
import com.example.diploma.data.model.News
import com.example.diploma.databinding.DiplomaFragmentMainPageBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment(),
    NewsAdapter.ClickListener {

    private var _binding: DiplomaFragmentMainPageBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModel()

    private lateinit var adapter: NewsAdapter
    private lateinit var recs: RecsAdapter

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DiplomaFragmentMainPageBinding.inflate(inflater, container, false)

        adapter = NewsAdapter(this)
        binding.newsRecyclerView.adapter = adapter

        recs = RecsAdapter()
        binding.carousel.adapter = recs
        recs.setList(CAROUSEL)

        val prefs: SharedPreferences by inject()

        binding.topPanel.nameTextView.text = prefs.getString(requireContext().getString(R.string.username), null)
        binding.topPanel.imageTop.setImageBitmap(getBitmap(resources, R.mipmap.ic_home_top))

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getNews()

        setUpObservers()
    }

    private fun getBitmap(res: Resources, image: Int): Bitmap? {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = false
        options.inSampleSize = 1
        options.inScaled = false
        options.inPreferredConfig = Bitmap.Config.ARGB_8888

        return BitmapFactory.decodeResource(res, image, options)
    }

    private fun setUpObservers() {
        viewModel.newsLiveData.observe(viewLifecycleOwner, newsObserver)
        viewModel.loadLiveData.observe(viewLifecycleOwner, loadObserver)
    }

    private val newsObserver = Observer<List<News?>> {
        it[0]?.title?.let { it1 -> Log.d("RECS", it1) }
        adapter.setList(it)
    }

    private val loadObserver = Observer<Boolean> {
        if (it) {
            binding.newsRecyclerView.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.newsRecyclerView.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
        }
        binding.newsRecyclerView.visibility = View.VISIBLE
    }

    override fun onClick(news: News) {
        val uri = Uri.parse(news.link)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}