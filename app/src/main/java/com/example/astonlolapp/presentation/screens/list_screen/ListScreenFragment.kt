package com.example.astonlolapp.presentation.screens.list_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.astonlolapp.data.remote.HeroApi
import com.example.astonlolapp.data.repository.RemoteDataSourceImpl
import com.example.astonlolapp.databinding.FragmentListScreenBinding
import com.example.astonlolapp.domain.model.Hero
import com.example.astonlolapp.domain.repository.RemoteDatasourceAbs
import com.example.astonlolapp.presentation.adapters.DefaultLoadStateAdapter
import com.example.astonlolapp.presentation.adapters.HeroAdapter
import com.example.astonlolapp.presentation.adapters.TryAgainAction
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.android.awaitFrame
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class ListScreenFragment :
    Fragment() {

    private var _binding: FragmentListScreenBinding? = null
    private val binding get() = _binding!!

    private val listScreenViewModel by viewModels<ListScreenViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentListScreenBinding.inflate(inflater, container, false)

        setupHeroList()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupHeroList() {
        val adapter = HeroAdapter()
        val tryAgainAction: TryAgainAction = { adapter.retry() }
        val footerAdapter = DefaultLoadStateAdapter(tryAgainAction)
        val adapterWithLoadState = adapter.withLoadStateFooter(footerAdapter)

        binding.recyclerview.layoutManager = LinearLayoutManager(context)
        binding.recyclerview.adapter = adapterWithLoadState
        (binding.recyclerview.itemAnimator as? DefaultItemAnimator)?.supportsChangeAnimations =
            false

        observeHeroes(adapter)


    }


    private fun observeHeroes(adapter: HeroAdapter) {
        Timber.d("observeHeroes is called")
        lifecycleScope.launch {
            listScreenViewModel.allHeroes.collectLatest { hero ->
                adapter.submitData(hero)

            }
        }
    }
}




