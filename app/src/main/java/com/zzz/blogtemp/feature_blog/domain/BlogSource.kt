package com.zzz.blogtemp.feature_blog.domain

import com.zzz.blogtemp.feature_blog.domain.model.Blog

interface BlogSource {

    suspend fun getBlogs(page :Int, pageSize : Int,onEndReached:()->Unit) : List<Blog>

}