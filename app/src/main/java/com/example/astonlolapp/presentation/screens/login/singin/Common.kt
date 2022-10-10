package com.example.astonlolapp.presentation.screens.login.singin

import android.app.Activity
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.example.astonlolapp.util.Constants.SERVER_CLIENT_ID
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import timber.log.Timber

@Composable
fun StartActivityForResult(
    key: Any,
    onResultReceived: (String) -> Unit,
    onDialogDismissed: () -> Unit,
    launcher: (ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult>) -> Unit
) {
    val activity = LocalContext.current as Activity
    val activityLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.StartIntentSenderForResult(),
            onResult = { result ->
                try {
                    if (result.resultCode == Activity.RESULT_OK) {
                        val oneTapClient = Identity.getSignInClient(activity)
                        val credentials = oneTapClient.getSignInCredentialFromIntent(result.data)
                        val tokenId = credentials.googleIdToken
                        if (tokenId != null) {
                            onResultReceived(tokenId)
                        }
                    }
                } catch (e: ApiException) {
                    when (e.statusCode) {
                        CommonStatusCodes.CANCELED -> {
                            Timber.d("BLACK SCRIM CLICKED DIALOG CLOSED")
                            onDialogDismissed()
                        }
                        CommonStatusCodes.NETWORK_ERROR -> {
                            Timber.d("One-Tap DIALOG CANCELED")
                            onDialogDismissed()
                        }
                        else -> {
                            Timber.d("${e.message}")
                            onDialogDismissed()
                        }
                    }
                }

            })
    LaunchedEffect(key1 = key) {
        launcher(activityLauncher)
    }
}

fun signIn(
    activity: Activity,
    launchActivityResult: (IntentSenderRequest) -> Unit,
    accountNotFound: () -> Unit
) {
    val oneTapClient = Identity.getSignInClient(activity)
    val signInRequest = BeginSignInRequest.builder().setGoogleIdTokenRequestOptions(
        BeginSignInRequest.GoogleIdTokenRequestOptions.builder().setSupported(true)
            .setServerClientId(SERVER_CLIENT_ID).setFilterByAuthorizedAccounts(true).build()
    ).setAutoSelectEnabled(true).build()

    oneTapClient.beginSignIn(signInRequest).addOnSuccessListener { result ->
        try {
            launchActivityResult(
                IntentSenderRequest.Builder(
                    result.pendingIntent.intentSender
                ).build()
            )
        } catch (e: Exception) {
            Timber.d("Couldn't start One Tap UI: ${e.message}")
        }
    }.addOnFailureListener {
        Timber.d("Sign In")
        signUp(
            activity = activity,
            launchActivityResult = launchActivityResult,
            accountNotFound = accountNotFound
        )
    }
}

fun signUp(
    activity: Activity,
    launchActivityResult: (IntentSenderRequest) -> Unit,
    accountNotFound: () -> Unit
) {
    val oneTapClient = Identity.getSignInClient(activity)
    val signInRequest = BeginSignInRequest.builder().setGoogleIdTokenRequestOptions(
        BeginSignInRequest.GoogleIdTokenRequestOptions.builder().setSupported(true)
            .setServerClientId(SERVER_CLIENT_ID).setFilterByAuthorizedAccounts(false).build()
    ).build()

    oneTapClient.beginSignIn(signInRequest).addOnSuccessListener { result ->
        try {
            launchActivityResult(
                IntentSenderRequest.Builder(
                    result.pendingIntent.intentSender
                ).build()
            )
        } catch (e: Exception) {
            Timber.d("Couldn't start One Tap UI: ${e.message}")
            accountNotFound()
        }
    }.addOnFailureListener {
        Timber.d("Sign Up")
        accountNotFound()
    }
}