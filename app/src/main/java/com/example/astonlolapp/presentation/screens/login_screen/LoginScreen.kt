package com.example.astonlolapp.presentation.screens.login_screen

import android.app.Activity
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.example.astonlolapp.presentation.screens.login_screen.singin.StartActivityForResult
import com.example.astonlolapp.presentation.screens.login_screen.singin.signIn
import timber.log.Timber

@Composable
fun LoginScreen(
    viewModel: LoginViewModel
) {

    val messageBarState by viewModel.messageBarState
    val signedInState by viewModel.signedInState.collectAsState()


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
            Timber.d(tokenId)
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
}
