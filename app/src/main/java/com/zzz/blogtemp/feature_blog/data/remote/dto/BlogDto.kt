package com.zzz.blogtemp.feature_blog.data.remote.dto

import com.zzz.blogtemp.feature_blog.domain.model.Blog
import kotlinx.serialization.Serializable

//@Serializable
data class BlogDto(
    val id : Int,
    val date : String,
    val title : Title,
    val link : String
)
//@Serializable
data class Title(
    val rendered : String
)
fun BlogDto.toBlog() =
    Blog(
        id = id,
        date = date,
        title = title.rendered,
        link = link
    )
