package com.zzz.blogtemp.feature_blog.domain

import com.zzz.blogtemp.feature_blog.domain.model.Blog
import com.zzz.blogtemp.feature_blog.domain.model.BlogEntity

interface BlogSource {

    suspend fun getBlogs(page :Int, pageSize : Int,onEndReached:()->Unit) : List<Blog>

    suspend fun getBlogsWithOfflineSupport(page :Int, pageSize : Int,onEndReached:()->Unit) : List<BlogEntity>

    suspend fun clearCachedData()

}