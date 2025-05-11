package com.zzz.blogtemp.feature_blog.presentation

import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.webkit.WebView
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.zzz.blogtemp.feature_blog.presentation.components.BackFab

@Composable
fun ReadBlogPage(
    blogLink : String,
    onBack:()->Unit,
    modifier: Modifier = Modifier
) {
    Box(){
        AndroidView(
            factory = {
                WebView(it).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        MATCH_PARENT,
                        MATCH_PARENT,
                    )
                }
            },
            update = {
                it.loadUrl(blogLink)
            }
        )
        BackFab(
            onBack = onBack,
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }

}

