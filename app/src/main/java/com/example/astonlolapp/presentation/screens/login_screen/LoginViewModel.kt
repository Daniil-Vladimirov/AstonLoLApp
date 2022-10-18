package com.example.astonlolapp.presentation.screens.login_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.astonlolapp.domain.model.ApiRequest
import com.example.astonlolapp.domain.model.ApiResponse
import com.example.astonlolapp.domain.model.MessageBarState
import com.example.astonlolapp.domain.use_cases.UseCases
import com.example.astonlolapp.util.RequestState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val useCases: UseCases) : ViewModel() {

    private val _signedInState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val signedInState = _signedInState.asStateFlow()

    private var _apiResponse: MutableStateFlow<RequestState<ApiResponse>> =
        MutableStateFlow(RequestState.Idle)
    val apiResponse = _apiResponse.asStateFlow()


    private val _messageBarState: MutableState<MessageBarState> = mutableStateOf(MessageBarState())
    val messageBarState: State<MessageBarState> = _messageBarState

    init {
        viewModelScope.launch {
            useCases.readSignedInUseCase().collect { signedIn ->
                _signedInState.value = signedIn
            }
        }
    }

    fun saveSingedInState(signedInState: Boolean) {
        viewModelScope.launch {
            useCases.saveSignedInStateUseCase(signedIn = signedInState)
        }
    }

    fun updateMessageBarState() {
        _messageBarState.value = MessageBarState(error = MessageBarException())
    }

    fun verifyTokenId(apiRequest: ApiRequest) {
        _apiResponse.value = RequestState.Loading
        Timber.d("${_apiResponse.value}")
        try {
            viewModelScope.launch {
                val response =
                    useCases.tokenVerificationUseCase(apiRequest = apiRequest)
                _apiResponse.value = RequestState.Success(data = response)
                Timber.d("${_apiResponse.value}")
                _messageBarState.value =
                    MessageBarState(message = response.message, error = response.error)
            }

        } catch (e: Exception){
            _apiResponse.value = RequestState.Error(t = e)
            _messageBarState.value = MessageBarState(error = e)
        }
    }
}


class MessageBarException(
    override val message: String? = "No accounts were found"
) : Exception()