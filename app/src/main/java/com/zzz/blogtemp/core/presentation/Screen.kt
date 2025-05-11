package com.zzz.blogtemp.core.presentation

import kotlinx.serialization.Serializable

sealed class Screen {
    @Serializable
    data object HomeScreen : Screen()

    @Serializable
    data class ReadScreen(val link : String) : Screen()
}