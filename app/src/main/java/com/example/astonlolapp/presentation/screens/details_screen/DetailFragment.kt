package com.example.astonlolapp.presentation.screens.details_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.load
import com.example.astonlolapp.R
import com.example.astonlolapp.databinding.FragmentDetailBinding
import com.example.astonlolapp.databinding.FragmentDetailMotionBinding
import com.example.astonlolapp.util.Constants
import com.example.astonlolapp.util.Constants.BASE_URL
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailMotionBinding? = null
    private val binding get() = _binding!!

    private val detailScreenViewModel by viewModels<DetailViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailMotionBinding.inflate(inflater, container, false)


        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                detailScreenViewModel.selectedHero.collect { hero ->
                    binding.background.load("${BASE_URL}${hero?.image}")
                }
            }
        }

        coordinateMotion(binding = binding)




        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun coordinateMotion(binding: FragmentDetailMotionBinding) {
        val appBarLayout: AppBarLayout = binding.appbarLayout
        val motionLayout: MotionLayout = binding.motionLayout

        val listener = AppBarLayout.OnOffsetChangedListener { unused, verticalOffset ->
            val seekPosition = -verticalOffset / appBarLayout.totalScrollRange.toFloat()
            motionLayout.progress = seekPosition
        }
        appBarLayout.addOnOffsetChangedListener(listener)

    }
}

