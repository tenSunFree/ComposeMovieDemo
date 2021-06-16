package com.example.composemoviedemo.home

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.example.composemoviedemo.common.util.LoadImageBackground
import com.example.composemoviedemo.R

@Composable
fun HomeScreen() {
    LoadImageBackground(
        url = R.drawable.icon_home,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillWidth,
        contentDescription = "home"
    )
}