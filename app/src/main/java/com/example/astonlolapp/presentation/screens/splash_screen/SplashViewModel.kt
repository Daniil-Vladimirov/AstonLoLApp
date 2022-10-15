package com.example.astonlolapp.presentation.screens.splash_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astonlolapp.domain.model.ScreenState
import com.example.astonlolapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    val useCases: UseCases,
) : ViewModel() {

    private val _onBoardingState = useCases.readOnboardingUseCase()
    private val _signedInState = useCases.readSignedInUseCase()

    private val _screenState: MutableStateFlow<ScreenState> =
        MutableStateFlow(ScreenState(onBoardingState = false, loginState = false))
    val screenState: StateFlow<ScreenState> = _screenState


    init {
        readOnScreenState()
        Timber.d("${screenState.value.onBoardingState}")
    }
    private fun readOnScreenState() {
        viewModelScope.launch {
            _onBoardingState.combine(_signedInState) { onBoardingState, signedInState ->
                val screenState =
                    ScreenState(onBoardingState = onBoardingState, loginState = signedInState)
                screenState
            }.collectLatest { screenState ->
                _screenState.value = screenState
            }
        }
    }
}