package com.compose.walleapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.compose.module.webview.WebView
import com.compose.module.webview.rememberWebViewState
import com.compose.walleapp.ui.components.video.VideoPlayer
import com.compose.walleapp.ui.components.video.rememberVodController
import com.compose.walleapp.viewmodel.VideoViewModel

/**
 * @author  walle
 * @date    2022/11/11
 * @desc    .
 */

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun VideoDetailScreen(videoViewModel: VideoViewModel= viewModel(),onBack: () -> Unit) {

    val vodController = rememberVodController()
    LaunchedEffect(vodController){
        vodController.startPlay(videoViewModel.videoUrl)
    }

    val webViewState = rememberWebViewState(data = videoViewModel.videoDesc)
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = "视频详情",
                    fontSize = 18.sp
                )
            },
            navigationIcon = {
                Icon(
                    imageVector = Icons.Default.NavigateBefore,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            onBack()
                        }
                        .padding(8.dp)
                )
            },
        )
    },
        modifier = Modifier
            .background(color = MaterialTheme.colors.primary)
            .statusBarsPadding()
    ) {

        Column(modifier = Modifier.fillMaxWidth()) {
            //视频区域
            Box(modifier = Modifier.height(200.dp)){
                VideoPlayer(vodController)
            }
            
            //简介
            
            WebView(state = webViewState)

        }

    }
}