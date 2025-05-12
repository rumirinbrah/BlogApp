package com.zzz.blogtemp.feature_blog.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zzz.blogtemp.feature_blog.domain.BlogSource
import com.zzz.blogtemp.feature_blog.domain.model.Blog
import com.zzz.blogtemp.feature_blog.domain.model.BlogEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BlogViewModel(
    private val blogSource: BlogSource
) : ViewModel() {

    //state vars for no offline support
    private val _blogs = MutableStateFlow(emptyList<Blog>())
    val blogs = _blogs.asStateFlow()

    //state vars for offline support
    private val _cachedBlogs = MutableStateFlow(emptyList<BlogEntity>())
    val cachedBlogs = _cachedBlogs.asStateFlow()

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()


    //custom paging
    private var page = 1
    private var pageSize = 10

    init {
        getBlogsWithCache()
    }

    fun onAction(action: HomeActions) {
        when (action) {
            HomeActions.OnLoadBlogs -> {
                //getBlogs()
                getBlogsWithCache()
            }

            HomeActions.OnLoadMore -> {
                //loadMore()
                loadMoreWithCache()
            }
        }
    }

    /**
     * Methods for offline cache
     */
    private fun getBlogsWithCache(){
        viewModelScope.launch {
            _state.update { it.copy(loading = true) }
            _cachedBlogs.update {
                blogSource.getBlogsWithOfflineSupport(page,pageSize, onEndReached = {
                    println("END Reached, nothing more to load!")
                })
            }
            _state.update { it.copy(loading = false) }

        }
    }
    /**
     * Methods for offline cache
     */
    private fun loadMoreWithCache(){
        if (_state.value.refreshing || _state.value.loading) {
            return
        }
        viewModelScope.launch {
            _state.update {
                it.copy(refreshing = true)
            }
            //delay(4000)
            page += 1
            blogSource.getBlogsWithOfflineSupport(page,pageSize, onEndReached = {}).apply {
                _cachedBlogs.update {
                    it + this
                }
            }
            _state.update {
                it.copy(refreshing = false)
            }
        }
    }


    /**
     * Methods for NO OFFLINE cache
     */
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
    /**
     * Methods for NO OFFLINE cache
     */
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


}