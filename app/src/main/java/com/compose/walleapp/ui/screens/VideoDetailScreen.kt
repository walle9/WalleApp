package com.compose.walleapp.ui.screens

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.compose.module.webview.WebView
import com.compose.module.webview.rememberWebViewState
import com.compose.walleapp.ui.components.video.VideoPlayer
import com.compose.walleapp.ui.components.video.rememberVodController
import com.compose.walleapp.ui.theme.Blue700
import com.compose.walleapp.viewmodel.VideoViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * @author  walle
 * @date    2022/11/11
 * @desc    .
 */

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun VideoDetailScreen(videoViewModel: VideoViewModel = viewModel(), onBack: () -> Unit) {

    val systemUiController = rememberSystemUiController()

    val vodController = rememberVodController(
        coverUrl = videoViewModel.coverUrl,
        videoUrl = videoViewModel.videoUrl

    )

    var videoBoxModifier by remember {
        mutableStateOf(
            Modifier.aspectRatio(16 / 9f)
        )
    }

    val configuration = LocalConfiguration.current

    var scaffoldModifier by remember {
        mutableStateOf(
            Modifier.alpha(1f)
        )
    }



    val webViewState = rememberWebViewState(data = videoViewModel.videoDesc)
    Scaffold(
        topBar = {
            if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
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
            }
        },
        modifier = scaffoldModifier
    ) {


        Column(modifier = Modifier
            .fillMaxWidth()
            ) {

            LaunchedEffect(configuration.orientation) {
                vodController.restore()
                if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    videoBoxModifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16 / 9f)
                    systemUiController.isSystemBarsVisible = true
                    scaffoldModifier = Modifier
                        .background(Blue700)
                        .statusBarsPadding()
                } else {
                    videoBoxModifier = Modifier.fillMaxSize()
                    systemUiController.isSystemBarsVisible = false
                    systemUiController.setSystemBarsColor(Color.Transparent)
                    scaffoldModifier = Modifier
                }
            }


            //视频区域
            Box(modifier = videoBoxModifier) {
                VideoPlayer(vodController)
            }

            //简介
            //WebView(state = webViewState)

        }


    }
}