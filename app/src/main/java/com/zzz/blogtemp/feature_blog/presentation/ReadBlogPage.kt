package com.zzz.blogtemp.feature_blog.presentation

import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.webkit.WebView
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.zzz.blogtemp.feature_blog.presentation.components.BackFab
import kotlinx.coroutines.delay

@Composable
fun ReadBlogPage(
    blogLink : String,
    onBack:()->Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val webView = remember { WebView(context) }

    LaunchedEffect(Unit) {
        delay(1000)
        webView.loadUrl(blogLink)
    }

    Box(){
        AndroidView(
            factory = {
                webView.apply {
                    layoutParams = ViewGroup.LayoutParams(
                        MATCH_PARENT,
                        MATCH_PARENT,
                    )
                }
            }
        )
        BackFab(
            onBack = onBack,
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }

}

