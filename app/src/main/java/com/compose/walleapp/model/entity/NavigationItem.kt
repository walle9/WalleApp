package com.compose.walleapp.model.entity

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * @author  walle
 * @date    2022/9/23
 * @desc    导航栏对象
 */

data class NavigationItem(
    val title: String,      //底部导航栏的标题
    val icon: ImageVector   //底部导航栏的图标
)
