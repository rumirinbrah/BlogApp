package com.zzz.blogtemp.di

import androidx.room.Room
import com.zzz.blogtemp.feature_blog.data.local.BlogDatabase
import com.zzz.blogtemp.feature_blog.data.remote.BlogApi
import com.zzz.blogtemp.feature_blog.data.repo.BlogSourceImpl
import com.zzz.blogtemp.feature_blog.domain.BlogSource
import com.zzz.blogtemp.feature_blog.presentation.BlogViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    //API
    single<BlogApi> {
        Retrofit.Builder()
            .baseUrl("https://blog.vrid.in/wp-json/wp/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BlogApi::class.java)
    }

    single <BlogDatabase>{
        Room.databaseBuilder(
            androidContext(),
            BlogDatabase::class.java,
            BlogDatabase.DB_NAME
        ).build()
    }

    single<BlogSource> {
        val db = get<BlogDatabase>()
        BlogSourceImpl(blogApi = get(), blogDao = db.blogDao)
    }

    viewModel {
        BlogViewModel(blogSource = get())
    }

}