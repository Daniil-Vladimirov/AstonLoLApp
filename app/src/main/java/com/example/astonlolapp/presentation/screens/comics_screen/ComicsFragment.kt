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
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.astonlolapp.databinding.FragmentComicsBinding
import com.example.astonlolapp.util.simpleScan
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ComicsFragment :
    Fragment() {


    private var _binding: FragmentComicsBinding? = null
    private val binding get() = _binding!!
    private val comicsScreenViewModel by viewModels<ComicsViewModel>()

    private lateinit var comicsAdapter: ComicsPagingAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        comicsAdapter = ComicsPagingAdapter()

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
        with(binding.comicsRecyclerView) {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    LinearLayoutManager.VERTICAL
                )
            )
           adapter = comicsAdapter
        }
    }

    private fun loadDataFromCache() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                comicsScreenViewModel.comicsCache.collectLatest { comics ->
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


    @OptIn(ExperimentalCoroutinesApi::class)
    private fun handleLoadingState() {
        lifecycleScope.launch {
            comicsAdapter.loadStateFlow.simpleScan(2).collectLatest { (prevState, currentState) ->
                binding.comicsErrorMsg.isVisible = (currentState?.refresh is LoadState.Error ||
                        prevState?.refresh is LoadState.Error) && comicsAdapter.itemCount < 1
                binding.comicsRetryButton.isVisible = (currentState?.refresh is LoadState.Error ||
                        prevState?.refresh is LoadState.Error) && comicsAdapter.itemCount < 1
                binding.comicsProgressBar.isVisible =
                    currentState?.refresh is LoadState.Loading && comicsAdapter.itemCount < 1
                binding.comicsSwipeToRefresh.isRefreshing =
                    currentState?.refresh is LoadState.Loading && binding.comicsProgressBar.isVisible == false
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

