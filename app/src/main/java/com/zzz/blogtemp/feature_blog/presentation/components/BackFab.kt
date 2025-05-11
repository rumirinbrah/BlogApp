package com.zzz.blogtemp.feature_blog.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun BackFab(
    onBack:()->Unit,
    size : Dp = 50.dp,
    modifier: Modifier = Modifier
) {
    Box(
        modifier
            .padding(16.dp)
            .size(size)
            .clip(CircleShape)
            .background(Color(0x830879B2))
            .clickable {
                onBack()
            }
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ){
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack ,
            contentDescription = null,
            tint = Color.White
        )
    }
}