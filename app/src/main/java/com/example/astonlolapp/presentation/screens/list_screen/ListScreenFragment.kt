package com.example.astonlolapp.presentation.screens.list_screen

import HeroesPagingAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
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

    private val listScreenViewModel by viewModels<ListScreenViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        //Timber.tag("ListFragment").d("onCreate")
        super.onCreate(savedInstanceState)
        heroes = listScreenViewModel.allHeroes
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Timber.tag("ListFragment").d("onCreateView")
        // Inflate the layout for this fragment
        _binding = FragmentListScreenBinding.inflate(inflater, container, false)

        heroAdapter = HeroesPagingAdapter()

        setupRecyclerView(heroAdapter)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                heroes.collectLatest {
                    heroAdapter.submitData(it)
                }
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun setupRecyclerView(heroAdapter: HeroesPagingAdapter) {
        binding.recyclerview.adapter = heroAdapter
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
    }
}






