package com.zzz.blogtemp.feature_blog.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zzz.blogtemp.feature_blog.data.local.dao.BlogDao
import com.zzz.blogtemp.feature_blog.domain.model.BlogEntity

@Database(
    entities = [BlogEntity::class],
    version = 1,
    exportSchema = false
)
abstract class BlogDatabase :RoomDatabase(){

    abstract val blogDao : BlogDao

    companion object{
        const val DB_NAME = "blog_db"
    }

}