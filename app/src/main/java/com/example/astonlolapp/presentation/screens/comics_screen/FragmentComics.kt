package com.example.astonlolapp.presentation.screens.comics_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.astonlolapp.databinding.FragmentComicsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class FragmentComics :
    Fragment() {


    private var _binding: FragmentComicsBinding? = null
    private val binding get() = _binding!!
    private val comicsScreenViewModel by viewModels<ComicsViewModel>()

    private lateinit var comicsAdapter: ComicsPagingAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        comicsAdapter = ComicsPagingAdapter()
        Timber.tag("FragmentComics").d("OnCreate")

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentComicsBinding.inflate(inflater, container, false)
        binding.comicsRetryButton.setOnClickListener {
            startApiRequest()
        }

        loadDataFromCache()
        loadDataFromApi()
        handleLoadingState()
        setupSwipeToRefresh()

        setupRecyclerView(comicsAdapter)
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setupRecyclerView(comicsAdapter: ComicsPagingAdapter) {
        binding.comicsRecyclerView.adapter = comicsAdapter
        binding.comicsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun loadDataFromCache() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                comicsScreenViewModel.comics.collectLatest { comics ->
                    comicsAdapter.submitData(comics)
                    binding.comicsSwipeToRefresh.isRefreshing = false
                }
            }
        }
    }

    private fun loadDataFromApi() {
        comicsAdapter.addLoadStateListener { loadState ->
            if (loadState.source.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached && comicsAdapter.itemCount < 1) {
                startApiRequest()
            }
        }
    }


    private fun handleLoadingState() {
        lifecycleScope.launch {
            comicsAdapter.loadStateFlow.collectLatest { loadState ->
                binding.comicsErrorMsg.isVisible = comicsAdapter.itemCount < 1
                binding.comicsRetryButton.isVisible = comicsAdapter.itemCount < 1
                binding.comicsProgressBar.isVisible = loadState.source.refresh is LoadState.Loading
                binding.comicsSwipeToRefresh.isRefreshing =
                    comicsAdapter.itemCount < 1 || loadState.source.refresh !is LoadState.NotLoading


            }
        }
    }


    private fun setupSwipeToRefresh() {
        binding.comicsSwipeToRefresh.setOnRefreshListener {
            startApiRequest()
        }
    }

    private fun startApiRequest() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                comicsScreenViewModel.comicsApi.collectLatest { comics ->
                    comicsAdapter.submitData(comics)
                    binding.comicsSwipeToRefresh.isRefreshing = false
                }
            }
        }

    }


}

