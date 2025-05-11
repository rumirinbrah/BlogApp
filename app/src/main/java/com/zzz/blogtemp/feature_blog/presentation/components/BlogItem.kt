package com.zzz.blogtemp.feature_blog.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zzz.blogtemp.feature_blog.domain.model.Blog
import com.zzz.blogtemp.ui.theme.BlogTempTheme

@Composable
fun BlogItem(
    blog: Blog,
    background : Color,
    onBackground : Color,
    onClick:(String)->Unit,
    modifier: Modifier = Modifier,
) {

    Row(
        modifier
            .fillMaxWidth()
            .clip(Shapes().medium)
            .background(background)
            .clickable {
                onClick(blog.link)
            }
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            Modifier.weight(1f) ,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(blog.title, style = MaterialTheme.typography.titleMedium, color = onBackground)
            Text(blog.date, style = MaterialTheme.typography.bodySmall,color = onBackground.copy(alpha = 0.5f))
        }
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward ,
            contentDescription = null,
            tint = onBackground
        )
    }


}

@Preview
@Composable
private fun IDK() {
    BlogTempTheme {
        //BlogItem(blog = Blog(id=2,date="2024", title = "HE HEE", link = ""))
    }
}