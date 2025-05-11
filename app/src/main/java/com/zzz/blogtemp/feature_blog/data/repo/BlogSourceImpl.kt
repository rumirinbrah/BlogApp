package com.zzz.blogtemp.feature_blog.data.repo

import com.zzz.blogtemp.feature_blog.data.remote.BlogApi
import com.zzz.blogtemp.feature_blog.data.remote.dto.toBlog
import com.zzz.blogtemp.feature_blog.domain.BlogSource
import com.zzz.blogtemp.feature_blog.domain.model.Blog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BlogSourceImpl(
    private val blogApi: BlogApi
) : BlogSource {

    override suspend fun getBlogs(page: Int , pageSize: Int , onEndReached :()->Unit): List<Blog> {

        return withContext(Dispatchers.IO){
            try {

                val response = blogApi.getBlogsResponse(pageSize,page)
                if(response.isSuccessful){
                    val newData = response.body() ?: emptyList()
                    newData.map { it.toBlog() }
                }else{
                    onEndReached()
                    emptyList()
                }

//                blogApi.getBlogs(pageSize,page).map {
//                    it.toBlog()
//                }

            }catch (e : Exception){
                e.printStackTrace()
                emptyList()
            }
        }


    }
}