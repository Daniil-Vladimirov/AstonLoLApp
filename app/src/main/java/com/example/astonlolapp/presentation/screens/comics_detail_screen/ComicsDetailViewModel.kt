package com.example.astonlolapp.presentation.screens.comics_detail_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astonlolapp.di.ApplicationScope
import com.example.astonlolapp.domain.model.Comics
import com.example.astonlolapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ComicsDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    useCases: UseCases,
    @ApplicationScope iODispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _selectedComics = MutableStateFlow<Comics?>(null)
    val selectedComics: StateFlow<Comics?> = _selectedComics

    init {
        viewModelScope.launch(iODispatcher) {
            val comicsId = savedStateHandle.get<Int>("ComicsId")
            _selectedComics.value = comicsId?.let {
                useCases.getSelectedComicsUseCase(comicsId = comicsId)
            }
            Timber.d(selectedComics.value?.text.toString())
        }

    }
}