package com.zzz.blogtemp.feature_blog.data.repo

import com.zzz.blogtemp.feature_blog.data.local.dao.BlogDao
import com.zzz.blogtemp.feature_blog.data.remote.BlogApi
import com.zzz.blogtemp.feature_blog.data.remote.dto.toBlog
import com.zzz.blogtemp.feature_blog.data.remote.dto.toBlogEntity
import com.zzz.blogtemp.feature_blog.domain.BlogSource
import com.zzz.blogtemp.feature_blog.domain.model.Blog
import com.zzz.blogtemp.feature_blog.domain.model.BlogEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date

class BlogSourceImpl(
    private val blogApi: BlogApi,
    private val blogDao: BlogDao
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

    override suspend fun getBlogsWithOfflineSupport(
        page: Int ,
        pageSize: Int ,
        onEndReached: () -> Unit
    ): List<BlogEntity> {
        return withContext(Dispatchers.IO){

            //CHECK LOCAL DB
            val offset= (page-1)*pageSize
            val cachedBlogs = blogDao.getPagedBlogs(pageSize,offset)

            if(cachedBlogs.isNotEmpty()){

                val blog = cachedBlogs.first()
                val lastCachedDate = Date(blog.lastCached)
                val today = Date(System.currentTimeMillis())
                if((today.date - lastCachedDate.date) >= 2){
                    println("CLEARING CACHED BLOGS")
                    clearCachedData()
                }else{
                    return@withContext cachedBlogs
                }
            }
            try {
                //no offline data available, load from api
                val response = blogApi.getBlogsResponse(pageSize,page)
                if(response.isSuccessful){
                    val newData = response.body() ?: emptyList()
                    val mappedData = newData.map { it.toBlogEntity() }

                    blogDao.insertBlogs(mappedData)

                    return@withContext mappedData
                }else{
                    onEndReached()
                    return@withContext emptyList()
                }


            }catch (e : Exception){
                e.printStackTrace()
                return@withContext emptyList()
            }
        }
    }

    override suspend fun clearCachedData() {
        withContext(Dispatchers.IO){
            blogDao.clearCachedBlogs()
        }
    }

}