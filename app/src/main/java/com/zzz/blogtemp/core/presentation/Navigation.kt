package com.zzz.blogtemp.core.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.zzz.blogtemp.feature_blog.presentation.HomePage
import com.zzz.blogtemp.feature_blog.presentation.ReadBlogPage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigation(

) {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text("Blog App")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { innerPadding ->
        NavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            startDestination =Screen.HomeScreen
        ) {
            composable<Screen.HomeScreen> {
                HomePage(
                    onReadBlog = {link->
                        println(link)
                        navController.navigate(Screen.ReadScreen(link))
                    }
                )
            }
            composable<Screen.ReadScreen> {
                val data = it.toRoute<Screen.ReadScreen>()
                ReadBlogPage(
                    blogLink = data.link ,
                    onBack = {navController.navigateUp()}
                )
            }
        }
    }

}