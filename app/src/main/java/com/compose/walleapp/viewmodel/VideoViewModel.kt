package com.compose.walleapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.compose.walleapp.model.entity.ArticleEntity
import com.compose.walleapp.model.entity.VideoEntity
import com.compose.walleapp.model.service.VideoService

/**
 * @author  walle
 * @date    2022/9/27
 * @desc    .
 */

class VideoViewModel : ViewModel() {

    var infoLoaded by mutableStateOf(false)
        private set

    //文章数据列表
    var list by mutableStateOf(
        listOf(
            VideoEntity(
                title = "9月27日，我国在太原卫星发射中心使用长征六号运载火箭，以“一箭三星”方式，成功将试验十六号A/B星和试验十七号卫星发射升空。",
                type = "火箭",
                imageUrl = "https://docs.bughub.icu/compose/assets/banner1.webp",
                duration = "00:02:00"
            )
        )
    )
        private set

    private val videoService = VideoService.instance()
    private val pageSize = 10
    private var pageOffset = 1

    //第一页数据是否加载成功
    var listLoaded by mutableStateOf(false)
        private set

    //是否正在刷新
    var refreshing by mutableStateOf(false)
        private set

    //是否能加载更多
    private var hasMore = false

    suspend fun fetchVideo() {
        var res = videoService.list(pageOffset, pageSize)
        //delay(1000)
        if (res.code == 0 && res.data != null) {
            var tempList = mutableListOf<VideoEntity>()
            if (pageOffset != 1) {
                tempList.addAll(list)
            }
            tempList.addAll(res.data!!)
            list = tempList
            listLoaded = true
            hasMore = pageOffset < 10
        } else {
            val massage = res.massage
            pageOffset--
            if (pageOffset <= 1) {
                pageOffset = 1
            }
        }

        refreshing = false
    }

    suspend fun fetchInfo() {
        val res = videoService.videoInfo("")
        if (res.code == 0 && res.data != null) {

            videoDesc =
                """$htmlHeader
            <h5 style = "color:#333333;font-size:16px;">$videoTitle</h5>
            ${res.data.desc}
        $htmlFooter
    """.trimIndent()

            videoUrl = res.data.video ?: ""
            coverUrl = res.data.imageUrl
            videoTitle = res.data.title

            infoLoaded= true

        } else {
            val massage = res.massage
        }
    }

    /**
     * 下拉刷新
     */
    suspend fun refresh() {
        pageOffset = 1
        refreshing = true
        fetchVideo()
    }

    /**
     * 加载更多
     */
    suspend fun loadMore() {
        if (hasMore) {
            pageOffset++
            fetchVideo()
        }
    }

    var videoUrl by mutableStateOf("http://vfx.mtime.cn/Video/2019/03/09/mp4/190309153658147087.mp4")
        private set

    var coverUrl by mutableStateOf("https://docs.bughub.icu/compose/assets/banner1.webp")
        private set

    private var videoTitle by mutableStateOf("习近平主持中央政治局常委会会议 分析新冠肺炎疫情形势 部署从严抓好疫情防控工作")

    //HTML头部
    private val htmlHeader = """
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title></title>
            <style>
                img {
                    max-width: 100% !important;
                }
            </style>
        </head>
        <body>
    """.trimIndent()

    //html尾部
    private val htmlFooter = """
        </body>
        </html>
    """.trimIndent()

    var videoDesc by mutableStateOf(
        """$htmlHeader
            <h5 style = "color:#333333;font-size:16px;">$videoTitle</h5>
        $htmlFooter
    """.trimIndent()
    )


}