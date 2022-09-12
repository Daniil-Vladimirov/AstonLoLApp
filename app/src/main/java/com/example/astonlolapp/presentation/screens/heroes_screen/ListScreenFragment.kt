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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.astonlolapp.databinding.FragmentListScreenBinding
import com.example.astonlolapp.domain.model.Hero
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

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
        Timber.d("onCreate")
        // Inflate the layout for this fragment
        _binding = FragmentListScreenBinding.inflate(inflater, container, false)

        binding.retryButton.setOnClickListener {
            fetchHeroes()
        }
        initMembers()
        setupViews(binding)
        fetchHeroes()
        handleLoadingState()
        return binding.root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setupViews(binding: FragmentListScreenBinding) {
        recyclerView = binding.recyclerview
        binding.recyclerview.layoutManager = LinearLayoutManager(context)
        binding.recyclerview.adapter = heroAdapter

    }

    private fun initMembers() {
        Timber.d("InitMembers")
        heroes = listScreenViewModel.allHeroes
        heroAdapter = HeroesPagingAdapter()
    }

    private fun fetchHeroes() {
        Timber.d("fetchHeroes")
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                heroes.collectLatest {
                    heroAdapter.submitData(it)
                }
            }
        }
    }

    private fun handleLoadingState() {
        lifecycleScope.launch {
            heroAdapter.loadStateFlow.collectLatest { loadState ->
                binding.errorMsg.isVisible = heroAdapter.itemCount < 1
                binding.retryButton.isVisible = heroAdapter.itemCount < 1
                binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading

            }
        }

    }
}









