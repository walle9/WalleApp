package com.compose.walleapp.extension


import android.util.Log
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.*
import kotlinx.coroutines.flow.collect

/**
 * @param buffer 预加载的条目数
 */
@Composable
fun LazyListState.OnBottomReached(buffer: Int = 1, loadMore: () -> Unit) {

    require(buffer>0) {
        "buffer 值必须是正整数"
    }

    //是否应该加载更多
    val shouldLoadMore = remember {
        //由另一状态计算派生
        derivedStateOf {
            //获取最后显示的item
            var visibleItem = layoutInfo.visibleItemsInfo.lastOrNull() ?: return@derivedStateOf true

            //如果是最后一个item,说明已经触底,需要加载更多
            visibleItem.index == layoutInfo.totalItemsCount - 1 - buffer

        }
    }

    LaunchedEffect(shouldLoadMore) {

        //文档 https://developer.android.google.cn/jetpack/compose/side-effects#snapshotFlow
        snapshotFlow {
            shouldLoadMore.value
        }.collect {
            if (it) {
                loadMore()
            }
        }


    }
}