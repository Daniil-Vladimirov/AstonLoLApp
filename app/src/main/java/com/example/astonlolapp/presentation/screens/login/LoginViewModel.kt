package com.example.astonlolapp.presentation.screens.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astonlolapp.domain.model.MessageBarState
import com.example.astonlolapp.domain.use_cases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val useCases: UseCases) : ViewModel() {

    private val _signedInState: MutableState<Boolean> = mutableStateOf(false)
    val signedInState: State<Boolean> = _signedInState

    private val _messageBarState: MutableState<MessageBarState> = mutableStateOf(MessageBarState())
    val messageBarState: State<MessageBarState> = _messageBarState

    init {
        viewModelScope.launch {
            useCases.readSignedInUseCase().collect { signedIn ->
                _signedInState.value = signedIn
            }
        }
    }
    fun saveSingedInState(signedInState: Boolean){
        viewModelScope.launch {
            useCases.saveSignedInStateUseCase(signedIn = signedInState)
        }
    }
}