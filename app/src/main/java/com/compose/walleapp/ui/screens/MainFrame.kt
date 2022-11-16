package com.compose.walleapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.compose.walleapp.model.entity.NavigationItem

/**
 * @author  walle
 * @date    2022/9/23
 * @desc    .
 */

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainFrame(
    onNavigateToArticle:()->Unit = {},
    onNavigateToVideo:()->Unit = {},
) {

    val navigationItems = listOf(
        NavigationItem(title = "学习", icon = Icons.Filled.Home),
        NavigationItem(title = "任务", icon = Icons.Filled.DateRange),
        NavigationItem(title = "我的", icon = Icons.Filled.Person)
    )

    var currentNavigationIndex by remember {
        mutableStateOf(0)
    }



    Scaffold(
        bottomBar = {
            BottomNavigation(
                backgroundColor = MaterialTheme.colors.surface,
                modifier = Modifier.navigationBarsPadding()
            ) {
                navigationItems.forEachIndexed { index, navigationItem ->
                    BottomNavigationItem(
                        selected = currentNavigationIndex == index,
                        onClick = {
                            currentNavigationIndex = index
                        },
                        icon = { Icon(imageVector = navigationItem.icon, contentDescription = null) },
                        label = {
                            Text(text = navigationItem.title)
                        },
                        selectedContentColor = Color(0xFF149EE7),
                        unselectedContentColor = Color(0xFF999999)
                    )
                }
            }
        }

    ) {

        Box(modifier = Modifier.padding(it)) {
            when (currentNavigationIndex) {
                0 -> StudyScreen(onNavigateToArticle= onNavigateToArticle, onNavigateToVideo = onNavigateToVideo)
                1 -> TaskScreen()
                2 -> MineScreen()
            }

        }
    }

}

@Preview
@Composable
fun MainFramePreview() {
    MainFrame()
}