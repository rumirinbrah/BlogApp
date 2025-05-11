package com.zzz.blogtemp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.zzz.blogtemp.core.presentation.Navigation
import com.zzz.blogtemp.feature_blog.presentation.components.BackFab
import com.zzz.blogtemp.ui.theme.BlogTempTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BlogTempTheme {
                Navigation()
//                Box(Modifier.fillMaxSize().navigationBarsPadding()){
//
//                }
            }
        }
    }
}

