package com.compose.walleapp.viewmodel

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.SmartDisplay
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.compose.walleapp.model.entity.Category
import com.compose.walleapp.model.entity.DataType
import com.compose.walleapp.model.entity.SwiperEntity

/**
 * @author  walle
 * @date    2022/9/26
 * @desc    .
 */

class MainViewModel : ViewModel() {

    //分类数据
    val categories by mutableStateOf(
        listOf(
            Category("思想政治"),
            Category("法律法规"),
            Category("职业道德"),
            Category("诚信自律")
        )
    )

    //当前分类下标
    var categoryIndex by mutableStateOf(0)
        private set

    /**
     * 更新分类下标
     * @param index 下标
     */
    fun updateCategoryIndex(index: Int) {
        categoryIndex = index
    }

    //类型数据
    val types by mutableStateOf(
        listOf(
            DataType("相关资讯", icon = Icons.Default.Description),
            DataType("视频课程", icon = Icons.Default.SmartDisplay)
        )
    )

    //当前类型下标
    var currentTypeIndex by mutableStateOf(0)
        private set

    //是否文章列表
    var showArticle:Boolean by mutableStateOf(true)
        private set

    /**
     * 更新类型下标
     * @param index 下标
     */
    fun updateCurrentTypeIndex(index: Int) {
        currentTypeIndex = index
        showArticle= currentTypeIndex==0
    }

    //轮播图数据
    val swiperData = listOf(
        SwiperEntity("https://docs.bughub.icu/compose/assets/banner1.webp"),
        SwiperEntity("https://docs.bughub.icu/compose/assets/banner2.webp"),
        SwiperEntity("https://docs.bughub.icu/compose/assets/banner3.webp"),
        SwiperEntity("https://docs.bughub.icu/compose/assets/banner4.jpg"),
        SwiperEntity("https://docs.bughub.icu/compose/assets/banner5.jpg")
    )

    val notifications = listOf(
        "东北地区大豆开始陆续收获",
        "黑龙江垦区今年把提高单产作为保粮食供给的主要途径",
        "通过良种良法配套",
        "让有限的耕地实现更大的产能"
    )

}