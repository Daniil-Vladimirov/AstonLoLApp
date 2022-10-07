package com.example.astonlolapp.presentation.screens.login

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val messageBarState by viewModel.messageBarState
    val signedInState by viewModel.signedInState
    val apiResponse by viewModel.apiResponse


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
/*    val activity = LocalContext.current as Activity
    ActivityResultContracts.StartActivityForResult(
        key = loginState,
        onResultReceived = { tokenId ->
            viewModel.verifyTokenId(apiRequest = ApiRequest(tokenId = tokenId))
        },
        onDialogDismissed = {
            viewModel.login(loginState = false)
        },
        launcher = { activityLauncher ->
            if (loginState) {
                signIn(
                    activity = activity,
                    launchActivityResult = { intentSenderRequest ->
                        activityLauncher.launch(intentSenderRequest)

                    },
                    accountNotFound = {
                        viewModel.login(loginState = false)
                        viewModel.throwExceptionToMessageState()
                        Timber.tag("LoginScreen").d(messageBarState.error)
                    }
                )
            }

        }
    )
    LaunchedEffect(key1 = apiResponse) {
        when (apiResponse) {
            is RequestState.Success -> {
                val result = (apiResponse as RequestState.Success<ApiResponse>).data.success
                if (result) {
                    navigateToDetailsScreen(navController = navController)
                } else {
                    viewModel.login(false)
                }
            }
            else -> {}
        }
    }
}

fun navigateToDetailsScreen(navController: NavHostController) {
    navController.navigate(Screen.Profile.route) {
        popUpTo(Screen.Login.route) {
            inclusive = true
        }
    }*/
}