package com.zzz.blogtemp.feature_blog.presentation

sealed class HomeActions {

    data object OnLoadMore : HomeActions()

    data object OnLoadBlogs : HomeActions()

}