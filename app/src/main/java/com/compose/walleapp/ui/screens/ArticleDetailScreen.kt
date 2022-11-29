@file:OptIn(ExperimentalMaterialApi::class)

package com.compose.walleapp.ui.screens

import android.annotation.SuppressLint
import android.transition.Slide
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.walleapp.viewmodel.ArticleViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.compose.module.webview.WebView
import com.compose.module.webview.rememberWebViewState
import kotlinx.coroutines.launch

/**
 * @author  walle
 * @date    2022/10/10
 * @desc    .
 */

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ArticleDetailScreen(viewModel: ArticleViewModel = viewModel(), onBack: () -> Unit) {
    
    LaunchedEffect(Unit){
        viewModel.fetchInfo()
    }

    val webViewState = rememberWebViewState(data = viewModel.content)
    var fontScale by remember {
        mutableStateOf(1.0f)
    }

    val scaffoldState = rememberBottomSheetScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "文章详情",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
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
                actions = {
                    Icon(
                        imageVector = Icons.Default.TextFields,
                        contentDescription = null,
                        modifier = Modifier
                            .clickable {
                                coroutineScope.launch {
                                    if (scaffoldState.bottomSheetState.isCollapsed) {
                                        scaffoldState.bottomSheetState.expand()
                                    } else {
                                        scaffoldState.bottomSheetState.collapse()
                                    }
                                }
                            }
                            .padding(8.dp)
                    )
                }
            )
        },
        modifier = Modifier
            .background(color = MaterialTheme.colors.primary)
            .statusBarsPadding(),
        sheetContent = {
            Column(modifier = Modifier.padding(8.dp)) {
                Text(text = "字体大小", fontSize = 16.sp)
                Slider(
                    value = fontScale,
                    onValueChange =
                    {
                        fontScale = it
                        webViewState.evaluateJavascript("document.body.style.zoom = $it")
                    },
                    steps = 3,
                    valueRange = 0.75f..1.75f
                )

                Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "较小", fontSize = 14.sp, color = Color(0xff999999))//0.75
                    Text(text = "标准", fontSize = 14.sp, color = Color(0xff999999))//1.0
                    Text(text = "普大", fontSize = 14.sp, color = Color(0xff999999))//1.25
                    Text(text = "超大", fontSize = 14.sp, color = Color(0xff999999))//1.5
                    Text(text = "特大", fontSize = 14.sp, color = Color(0xff999999))//1.75
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        },
        sheetPeekHeight = 0.dp

    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(), contentAlignment = Alignment.Center) {
            WebView(webViewState)
            if (!viewModel.infoLoaded) {
                CircularProgressIndicator()
            }

        }


    }
}