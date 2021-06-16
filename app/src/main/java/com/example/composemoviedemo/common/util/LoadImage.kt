package com.example.composemoviedemo.common.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.example.composemoviedemo.common.theme.compositedOnSurface
import com.example.composemoviedemo.common.util.Utils
import dev.chrisbanes.accompanist.coil.CoilImage
import dev.chrisbanes.accompanist.imageloading.ImageLoadState

@Composable
fun LoadImage(
    url: Any,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    placeholderColor: Color? = MaterialTheme.colors.compositedOnSurface(0.2f)
) {
    CoilImage(
        data = url,
        modifier = modifier.clip(CircleShape),
        contentDescription = contentDescription,
        contentScale = contentScale,
        fadeIn = true,
        onRequestCompleted = {
            when (it) {
                is ImageLoadState.Success -> Utils.logDebug(
                    Utils.TAG_NETWORK,
                    "Image succeed with source:${it.source}"
                )
                is ImageLoadState.Error -> Utils.logDebug(
                    Utils.TAG_NETWORK,
                    "Image error msg:${it.throwable.message}"/*, it.throwable*/
                )
                ImageLoadState.Loading -> Utils.logDebug(Utils.TAG_NETWORK, "Image loading")
                ImageLoadState.Empty -> Utils.logDebug(Utils.TAG_NETWORK, "Image empty")
            }
        },
        loading = {
            if (placeholderColor != null) {
                Spacer(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(placeholderColor)
                )
            }
        }
    )
}

@Composable
fun LoadImageBackground(
    url: Int,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    placeholderColor: Color? = MaterialTheme.colors.compositedOnSurface(0.2f)
) {
    CoilImage(
        data = url,
        modifier = modifier,
        contentDescription = contentDescription,
        contentScale = contentScale,
        fadeIn = true,
        onRequestCompleted = {
            when (it) {
                is ImageLoadState.Success -> Utils.logDebug(
                    Utils.TAG_NETWORK,
                    "Image succeed with source:${it.source}"
                )
                is ImageLoadState.Error -> Utils.logDebug(
                    Utils.TAG_NETWORK,
                    "Image error msg:${it.throwable.message}"/*, it.throwable*/
                )
                ImageLoadState.Loading -> Utils.logDebug(Utils.TAG_NETWORK, "Image loading")
                ImageLoadState.Empty -> Utils.logDebug(Utils.TAG_NETWORK, "Image empty")
            }
        },
        loading = {
            if (placeholderColor != null) {
                Spacer(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(placeholderColor)
                )
            }
        }
    )
}
