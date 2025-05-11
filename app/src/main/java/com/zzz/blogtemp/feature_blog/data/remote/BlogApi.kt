package com.zzz.blogtemp.feature_blog.data.remote

import com.zzz.blogtemp.feature_blog.data.remote.dto.BlogDto
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BlogApi {

    //?per_page=10&page=1
    @GET("posts")
    suspend fun getBlogs(
        @Query("per_page") pageSize : Int,
        @Query("page") currentPage : Int
    ) : List<BlogDto>

    @GET("posts")
    suspend fun getBlogsResponse(
        @Query("per_page") pageSize : Int,
        @Query("page") currentPage : Int
    ) : Response<List<BlogDto>>

}