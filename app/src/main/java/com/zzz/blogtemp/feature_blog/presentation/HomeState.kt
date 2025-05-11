package com.zzz.blogtemp.feature_blog.presentation

/**
 * @param loading - Represents initial loading
 * @param refreshing - Set when more pages are being loaded
 */
data class HomeState(
    val loading : Boolean =false,
    val refreshing : Boolean = false
)
