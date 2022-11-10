package com.compose.walleapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.compose.walleapp.ui.components.NavHostApp
import com.compose.walleapp.ui.screens.MainFrame
import com.compose.walleapp.ui.theme.WalleAppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@OptIn(ExperimentalAnimationApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //让内容,显示在状态栏和系统导航栏的后面:状态栏和导航栏会遮盖部分内容
        WindowCompat.setDecorFitsSystemWindows(window,false)

        setContent {
            WalleAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    //MainFrame()

                    val systemUiController = rememberSystemUiController()
                    LaunchedEffect(key1 = Unit) {
                        systemUiController.setStatusBarColor(Color.Transparent)
                    }

                    NavHostApp()
                }
            }


        }

    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WalleAppTheme {
        Greeting("Android")
    }
}