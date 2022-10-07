package com.example.astonlolapp.ui

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)

val LightGray = Color(0xFFD8D8D8)
val DarkGray = Color(0xFF2A2A2A)
val DeleteItem = Color(0xFFDF603F)

val Gray200 = Color(0xFF819ca9)
val Gray500 = Color(0xFF546e7a)
val Gray700 = Color(0xFF29434e)
val Teal200 = Color(0xFF03DAC5)
val ErrorRed = Color(0xFFFF6C60)
val InfoGreen = Color(0xFF00C096)
val LoadingBlue = Color(0xFF1A73E8)

val Colors.statusBarColor
    @Composable
    get() = if (isLight) Purple700 else Color.Black

val Colors.comicsDetailBackground
    @Composable
    get() = if (isLight) Color.White else Color.Black

val Colors.activeIndicatorColor
    @Composable
    get() = if (isLight) Purple500 else Purple700

val Colors.inactiveIndicatorColor
    @Composable
    get() = if (isLight) LightGray else DarkGray

val Colors.buttonBackgroundColor
    @Composable
    get() = if (isLight) Purple500 else Purple700

val Colors.topAppBarContentColor: Color
    @Composable
    get() = if (isLight) Color.White else LightGray

