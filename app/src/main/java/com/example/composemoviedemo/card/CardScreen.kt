package com.example.composemoviedemo.card

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.example.composemoviedemo.common.util.LoadImageBackground
import com.example.composemoviedemo.R

@Composable
fun CardScreen() {
    LoadImageBackground(
        url = R.drawable.icon_card,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillWidth,
        contentDescription = "card"
    )
}