package com.example.composemoviedemo.discount.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composemoviedemo.R
import com.example.composemoviedemo.common.util.LoadImage
import com.example.composemoviedemo.common.util.LoadImageBackground
import com.example.composemoviedemo.common.util.ShowDialog
import com.example.composemoviedemo.common.util.Utils
import com.example.composemoviedemo.discount.model.DiscountResponse
import com.example.composemoviedemo.discount.viewModel.DiscountViewModel

@ExperimentalFoundationApi
@Composable
fun DiscountScreen(viewModel: DiscountViewModel, onClick: (DiscountResponse.Item) -> Unit) {
    val context = LocalContext.current.applicationContext
    val initData by remember { mutableStateOf("") }
    if (!Utils.ensureNetworkAvailable(context, false)) {
        ShowDialog(R.string.search_dialog_tip, R.string.search_failure)
    }
    Column {
        LoadImageBackground(
            url = R.drawable.icon_discount_top_bar,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth,
            contentDescription = "card"
        )
        LaunchedEffect(initData) {
            viewModel.requestPhotos()
        }
        val response: State<DiscountResponse> = viewModel.response.observeAsState(
            DiscountResponse()
        )
        LazyVerticalGrid(
            modifier = Modifier
                .padding(top = 8.dp),
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(response.value) { movie ->
                Item(movie, onClick = {
                    onClick(movie)
                })
            }
        }
    }
}

@Composable
fun Item(movie: DiscountResponse.Item, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clickable(onClick = onClick).padding(bottom = 16.dp)
            .wrapContentSize(),
        contentAlignment = Alignment.Center
    ) {
        LoadImage(
            url = movie.url,
            modifier = Modifier.wrapContentWidth(),
            contentScale = ContentScale.Crop,
            contentDescription = movie.title
        )
        Text(
            text = movie.title,
            fontSize = 24.sp,
            modifier = Modifier.fillMaxSize(),
            color = Color.White,
        )
    }
}