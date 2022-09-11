package com.example.astonlolapp.presentation.screens.comics_detail_screen

import androidx.lifecycle.ViewModel
import com.example.astonlolapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ComicsDetailViewModel @Inject constructor(useCases: UseCases) : ViewModel() {
}