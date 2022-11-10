@file:OptIn(ExperimentalPagerApi::class, ExperimentalPagerApi::class)

package com.compose.walleapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.walleapp.viewmodel.MainViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.VerticalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import java.util.*

/**
 * @author  walle
 * @date    2022/9/27
 * @desc    .
 */
@Composable
fun NotificationContent(vm: MainViewModel) {

    //虚拟页数
    val virtualCount = Int.MAX_VALUE
    //实际页数
    val actualCount = vm.notifications.size
    //初始图片下标
    val initialIndex = virtualCount / 2

    val pagerState = rememberPagerState(initialPage = initialIndex)

    val rememberCoroutineScope = rememberCoroutineScope()

    DisposableEffect(key1 = Unit){
        val timer= Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                rememberCoroutineScope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage+1)
                }

            }

        },4000,4000)
        onDispose {
            timer.cancel()
        }
    }

    Row(
        modifier = Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(color = Color(0x22149EE7))
            .height(45.dp)
            .padding(horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "最新活动",
            color = Color(0xFF149EE7),
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.width(8.dp))

        VerticalPager(
            count = virtualCount,
            state = pagerState,
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.Start
        ) { page ->
            val actualIndex = (page-initialIndex).floorMod(actualCount)
            Text(
                text = vm.notifications[actualIndex],
                color = Color(0xff333333),
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "更多",
            fontSize = 14.sp,
            color = Color(0xff666666)
        )


    }

}

@Preview
@Composable
fun NotificationContentPreview() {
}
