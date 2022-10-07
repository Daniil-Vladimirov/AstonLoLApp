package com.example.astonlolapp.presentation.screens.login



import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.astonlolapp.ui.MEDIUM_PADDING
import com.example.astonlolapp.ui.topAppBarBackgroundColor
import com.example.astonlolapp.ui.topAppBarContentColor

@Composable
fun LoginTopAppBar() {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        title = {
            Text(
                modifier = Modifier.padding(start = MEDIUM_PADDING),
                text = "Sign in",
                color = MaterialTheme.colors.topAppBarContentColor
            )
        }
    )
}

@Composable
@Preview
fun LoginTopAppBarPreview() {
    LoginTopAppBar()
}