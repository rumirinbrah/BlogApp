package com.zzz.blogtemp.feature_blog.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "blog_table")
data class BlogEntity(
    @PrimaryKey
    val id : Int,
    val date : String,
    val title : String,
    val link : String,
    val lastCached : Long = System.currentTimeMillis()
)
