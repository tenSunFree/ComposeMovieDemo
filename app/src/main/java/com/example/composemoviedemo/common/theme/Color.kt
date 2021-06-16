package com.example.composemoviedemo.common.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver

@Composable
fun Colors.compositedOnSurface(alpha: Float): Color {
    return onSurface.copy(alpha = alpha).compositeOver(surface)
}

val toolBarColor = Color(0xFFFBDE4B)
val nameColor = Color(0xFF411445)
val itemCardColor = Color(0xFFf2eada)
val infoCardColor = itemCardColor
val infoButtonColor = nameColor
val infoColor = nameColor
val likeColor = Color(0xFF888888)
val likedColor = nameColor
val likeColorBg = Color(0xFFf2eada)
val purple200 = Color(0xFFBB86FC)
val purple500 = Color(0xFF6200EE)
val purple700 = Color(0xFF3700B3)
val teal200 = Color(0xFF03DAC5)
val pink100 = Color(0xFFFFF1F1)
val pink900 = Color(0xFF3f2c2c)
val gray = Color(0xFF232323)
val whit850 = Color.White.copy(alpha = .85f)
val whit150 = Color.White.copy(alpha = .15f)
val green900 = Color(0xFF2d3b2d)
val green300 = Color(0xFFb8c9b8)
val black = Color(0x1C1E28)
val darkBlue = Color(0x212E54)
val blue = Color(0x071C74)
val white = Color.White
val screenBGColor = darkBlue