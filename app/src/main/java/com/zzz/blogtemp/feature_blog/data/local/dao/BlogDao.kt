package com.zzz.blogtemp.feature_blog.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zzz.blogtemp.feature_blog.domain.model.BlogEntity

@Dao
abstract class BlogDao {

    @Query("delete from blog_table")
    abstract suspend fun clearCachedBlogs()

    @Query("select * from blog_table order by lastCached DESC LIMIT :pageSize OFFSET :offset")
    abstract suspend fun getPagedBlogs(pageSize :Int , offset : Int) : List<BlogEntity>

    @Query("select count(*) from blog_table")
    abstract suspend fun getSize():Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertBlogs(blogs : List<BlogEntity>)

}