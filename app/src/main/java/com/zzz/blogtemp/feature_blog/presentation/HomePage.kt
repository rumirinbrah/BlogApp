package com.zzz.blogtemp.feature_blog.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.zzz.blogtemp.feature_blog.presentation.components.BlogItem
import com.zzz.blogtemp.feature_blog.presentation.components.CustomProgressBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomePage(
    blogViewModel: BlogViewModel = koinViewModel(),
    onReadBlog : (String)->Unit,
) {

    val blogs by blogViewModel.blogs.collectAsStateWithLifecycle()
    val cachedBlogs by blogViewModel.cachedBlogs.collectAsStateWithLifecycle()

    val state by blogViewModel.state.collectAsStateWithLifecycle()


    Column(
        Modifier.fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if(state.loading){
            CustomProgressBar(
                color = MaterialTheme.colorScheme.onBackground,
                componentSize = 60.dp
            )
        }
        else{
            LazyColumn(
                Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    cachedBlogs,
                    key = {
                        it.id
                    }
                ) {
                    BlogItem(
                        blog = it ,
                        background = MaterialTheme.colorScheme.surfaceContainer ,
                        onBackground = MaterialTheme.colorScheme.onBackground,
                        onClick = {link->
                            onReadBlog(link)
                        },
                        modifier = Modifier.animateItem()
                    )
                }
                item {
                    AnimatedVisibility(state.refreshing) {
                        Box(
                            Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ){
                            CircularProgressIndicator()
                        }
                    }
                    AnimatedVisibility(!state.refreshing) {
                        Box(
                            Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ){
                            IconButton(
                                onClick = {
                                    blogViewModel.onAction(HomeActions.OnLoadMore)
                                }
                            ) {
                                Icon(
                                    imageVector =Icons.Default.Refresh ,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onBackground
                                )
                            }
                        }
                    }



                }
            }
        }


    }

}