package com.compose.walleapp.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.compose.walleapp.ui.theme.Blue200
import com.compose.walleapp.ui.theme.Blue700
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * @author  walle
 * @date    2022/9/23
 * @desc    .
 */

//标题栏高度
val appBarHeight = 56.dp

@Composable
fun TopAppBar(modifier: Modifier = Modifier, content: @Composable () -> Unit) {

    /*val systemUiController = rememberSystemUiController()
    LaunchedEffect(key1 = Unit) {
        systemUiController.setStatusBarColor(Color.Transparent)
    }*/



    //状态栏高度
    val statusBarHeightDp = LocalDensity.current.run {
        WindowInsets.statusBars.getTop(this).toDp()
    }

    Row(
        modifier = Modifier.background(
            brush = Brush.linearGradient(
                listOf(
                    Blue700,
                    Blue200
                )
            )
        )
            .fillMaxWidth()
            .height(appBarHeight + statusBarHeightDp)
            .padding(top = statusBarHeightDp) then modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        content()
    }
}

@Preview
@Composable
fun TopAppBarPreview() {
    TopAppBar {
        Text(text = "标题")
    }
}