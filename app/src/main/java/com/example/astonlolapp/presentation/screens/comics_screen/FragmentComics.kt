package com.example.astonlolapp.presentation.screens.comics_screen

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
import androidx.paging.PagingSource
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.astonlolapp.databinding.FragmentComicsBinding
import com.example.astonlolapp.domain.model.Comics
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentComics :
    Fragment() {


    private var _binding: FragmentComicsBinding? = null
    private val binding get() = _binding!!

    private lateinit var comics: Flow<PagingData<Comics>>
    private lateinit var comicsAdapter: ComicsPagingAdapter

    private val comicsScreenViewModel by viewModels<ComicsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        comics = comicsScreenViewModel.allComics
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentComicsBinding.inflate(inflater, container, false)

        comicsAdapter = ComicsPagingAdapter()

        setupRecyclerView(comicsAdapter)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                comics.collectLatest {
                    comicsAdapter.submitData(it)
                }
            }
        }

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
}