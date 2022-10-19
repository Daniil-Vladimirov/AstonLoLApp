package com.example.astonlolapp.presentation.screens.login_screen

import android.app.Activity
import android.content.Intent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.example.astonlolapp.MainActivity
import com.example.astonlolapp.domain.model.ApiRequest
import com.example.astonlolapp.domain.model.ApiResponse
import com.example.astonlolapp.presentation.screens.login_screen.singin.StartActivityForResult
import com.example.astonlolapp.presentation.screens.login_screen.singin.signIn
import com.example.astonlolapp.util.RequestState
import timber.log.Timber

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
) {

    val messageBarState by viewModel.messageBarState
    val signedInState by viewModel.signedInState.collectAsState()
    val apiResponse by viewModel.apiResponse.collectAsState()


    Scaffold(
        topBar = {
            LoginTopAppBar()
        },
        content = { padding ->
            LoginContent(
                messageBarState = messageBarState,
                loginState = signedInState,
                paddingValues = padding,
                onLoginClicked = {
                    viewModel.saveSingedInState(signedInState = true)
                }
            )

        }
    )
    val activity = LocalContext.current as Activity
    StartActivityForResult(
        key = signedInState,
        onResultReceived = { tokenId ->
            viewModel.verifyTokenId(apiRequest = ApiRequest(tokenId = tokenId))
        },
        onDialogDismissed = {
            viewModel.saveSingedInState(signedInState = false)
        },
        launcher = { activityLauncher ->
            if (signedInState) {
                signIn(activity = activity,
                    launchActivityResult = { intentSenderRequest ->
                        activityLauncher.launch(intentSenderRequest)
                    },
                    accountNotFound = {
                        viewModel.saveSingedInState(signedInState = false)
                        viewModel.updateMessageBarState()
                        Timber.d("${messageBarState.error}")

                    }
                )
            }
        }
    )
    LaunchedEffect(key1 = apiResponse) {
        Timber.d("$apiResponse in launch")
        when (apiResponse) {
            is RequestState.Success -> {
                val result = (apiResponse as RequestState.Success<ApiResponse>).data.success
                if (result) {
                    val intent = Intent(activity, MainActivity::class.java)
                    ContextCompat.startActivity(activity, intent, null)
                    activity.finish()
                } else {
                    viewModel.saveSingedInState(signedInState = false)
                }
            }
            else -> {}
        }
    }
}
