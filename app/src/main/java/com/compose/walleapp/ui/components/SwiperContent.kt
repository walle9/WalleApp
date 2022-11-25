@file:OptIn(ExperimentalPagerApi::class)

package com.compose.walleapp.ui.components

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.compose.walleapp.viewmodel.MainViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import kotlinx.coroutines.launch
import java.util.*

/**
 * @author  walle
 * @date    2022/9/26
 * @desc    轮播图
 */

@Composable
fun SwiperContent(vm: MainViewModel) {

    //虚拟页数
    val virtualCount = Int.MAX_VALUE
    //实际页数
    val actualCount = vm.swiperData.size
    //初始图片下标
    val initialIndex = virtualCount / 2

    val pagerState = rememberPagerState(initialPage = initialIndex)

    val rememberCoroutineScope = rememberCoroutineScope()

    DisposableEffect(key1 = Unit) {
        rememberCoroutineScope.launch {
            vm.swiperData()
        }


        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                if (vm.categoryLoaded) {
                    rememberCoroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            }

        }, 3000, 3000)
        onDispose {
            timer.cancel()
        }
    }

    //轮播图
    HorizontalPager(
        count = virtualCount,
        state = pagerState,
        //contentPadding = PaddingValues(horizontal = 16.dp),
        //itemSpacing = 16.dp,
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .clip(RoundedCornerShape(8.dp)),
        userScrollEnabled = vm.swiperLoaded
        ) { page ->
        val actualIndex = (page - initialIndex).floorMod(actualCount)
        AsyncImage(
            model = vm.swiperData[actualIndex].imageUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(7 / 3f)
                .placeholder(
                    !vm.swiperLoaded,
                    highlight = PlaceholderHighlight.shimmer(),
                    color = Color.LightGray
                ),
            contentScale = ContentScale.Crop
        )
    }

}


@Preview
@Composable
fun SwiperContentPreview() {
}

fun Int.floorMod(other: Int): Int = when (other) {
    0 -> this
    else -> this - floorDiv(other) * other
}



