package com.example.astonlolapp.presentation.screens.heroes_screen

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
import androidx.paging.PagingData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.WorkManager
import com.example.astonlolapp.databinding.FragmentListScreenBinding
import com.example.astonlolapp.domain.model.Hero
import com.example.astonlolapp.util.simpleScan
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
@AndroidEntryPoint
class ListScreenFragment :
    Fragment() {


    private var _binding: FragmentListScreenBinding? = null
    private val binding get() = _binding!!

    private lateinit var heroes: Flow<PagingData<Hero>>
    private lateinit var heroAdapter: HeroesPagingAdapter

    private lateinit var recyclerView: RecyclerView

    private val listScreenViewModel by viewModels<ListScreenViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentListScreenBinding.inflate(inflater, container, false)

        initMembers()
        setupViews(binding)
        fetchHeroes()
        handleLoadingState()
        setupSwipeToRefresh()

        binding.retryButton.setOnClickListener {
         fetchHeroes()
        }


        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setupViews(binding: FragmentListScreenBinding) {
        recyclerView = binding.recyclerview
        with(binding.recyclerview) {
            layoutManager = LinearLayoutManager(context)
            adapter = heroAdapter
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    LinearLayoutManager.VERTICAL
                )
            )

        }


    }

    private fun initMembers() {
        heroes = listScreenViewModel.allHeroes
        heroAdapter = HeroesPagingAdapter(listScreenViewModel)
    }

    private fun fetchHeroes() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                listScreenViewModel.allHeroes.collectLatest { heroes ->
                    heroAdapter.submitData(heroes)
                }
            }
        }
    }


    private fun handleLoadingState() {
        lifecycleScope.launch {
            heroAdapter.loadStateFlow.simpleScan(2).collectLatest { (prevState, currentState) ->
                binding.errorMsg.isVisible =
                    currentState?.refresh is LoadState.Error && heroAdapter.itemCount < 1 ||
                            prevState?.refresh is LoadState.Error && heroAdapter.itemCount < 1
                binding.retryButton.isVisible = currentState?.refresh is LoadState.Error
                        && heroAdapter.itemCount < 1 ||
                        prevState?.refresh is LoadState.Error && heroAdapter.itemCount < 1
                binding.progressBar.isVisible = currentState?.refresh is LoadState.Loading
                        && heroAdapter.itemCount < 1
                binding.swipeToRefreshLayout.isRefreshing =
                    currentState?.refresh is LoadState.Loading && binding.progressBar.isVisible == false
                            && heroAdapter.itemCount < 1

            }
        }
    }

    private fun setupSwipeToRefresh() {
        binding.swipeToRefreshLayout.setOnRefreshListener {
            fetchHeroes()
        }
    }

}









