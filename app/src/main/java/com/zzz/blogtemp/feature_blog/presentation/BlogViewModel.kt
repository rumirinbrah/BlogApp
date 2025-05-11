package com.zzz.blogtemp.feature_blog.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zzz.blogtemp.feature_blog.domain.BlogSource
import com.zzz.blogtemp.feature_blog.domain.model.Blog
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BlogViewModel(
    private val blogSource: BlogSource
) : ViewModel() {

    private val _blogs = MutableStateFlow(emptyList<Blog>())
    val blogs = _blogs.asStateFlow()

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()


    //custom paging
    private var page = 1
    private var pageSize = 10

    init {
        getBlogs()
    }

    fun onAction(action: HomeActions) {
        when (action) {
            HomeActions.OnLoadBlogs -> {
                getBlogs()
            }

            HomeActions.OnLoadMore -> {
                loadMore()
            }
        }
    }

    private fun loadMore() {
        if (_state.value.refreshing || _state.value.loading) {
            return
        }
        viewModelScope.launch {
            _state.update {
                it.copy(refreshing = true)
            }
            //delay(4000)
            page += 1
            blogSource.getBlogs(page,pageSize, onEndReached = {}).apply {
                _blogs.update {
                    it + this
                }
            }
            _state.update {
                it.copy(refreshing = false)
            }
        }
    }

    private fun getBlogs() {
        viewModelScope.launch {
            _state.update { it.copy(loading = true) }
            _blogs.update {
                blogSource.getBlogs(page,pageSize, onEndReached = {
                    println("ENDDDDDDDD")
                })
            }
            _state.update { it.copy(loading = false) }

        }
    }


}