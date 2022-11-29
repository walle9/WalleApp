package com.compose.walleapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.compose.walleapp.model.entity.ArticleEntity
import com.compose.walleapp.model.service.ArticleService

/**
 * @author  walle
 * @date    2022/9/27
 * @desc    .
 */

class ArticleViewModel : ViewModel() {

    val articleService = ArticleService.instance()

    var infoLoaded by mutableStateOf(false)
        private set

    private val pageSize = 10
    private var pageOffset = 1

    //使用这个不用写临时变量了
    var list1 = mutableStateListOf<String>()

    //文章数据列表
    var list by mutableStateOf(
        listOf(
            ArticleEntity("中国航天3天3连发中国航天3天3连发中国航天3天3连发", "澎湃新闻", "2022-09-27"),
            ArticleEntity("中国航天3天3连发中国航天3天3连发中国航天3天3连发", "澎湃新闻", "2022-09-27"),
            ArticleEntity("中国航天3天3连发中国航天3天3连发中国航天3天3连发", "澎湃新闻", "2022-09-27"),
            ArticleEntity("中国航天3天3连发", "澎湃新闻", "2022-09-27"),
            ArticleEntity("中国航天3天3连发中国航天3天3连发中国航天3天3连发", "澎湃新闻", "2022-09-27"),
            ArticleEntity("中国航天3天3连发中国航天3天3连发中国航天3天3连发", "澎湃新闻", "2022-09-27"),
            ArticleEntity("中国航天3天3连发", "澎湃新闻", "2022-09-27"),
            ArticleEntity("中国航天3天3连发中国航天3天3连发中国航天3天3连发", "澎湃新闻", "2022-09-27"),
            ArticleEntity("中国航天3天3连发中国航天3天3连发", "澎湃新闻", "2022-09-27"),
            ArticleEntity("中国航天3天3连发", "澎湃新闻", "2022-09-27"),
            ArticleEntity("中国航天3天3连发中国航天3天3连发", "澎湃新闻", "2022-09-27")
        )
    )
        private set

    //第一页数据是否加载成功
    var listLoaded by mutableStateOf(false)
        private set

    //是否正在刷新
    var refreshing by mutableStateOf(false)
        private set

    //是否能加载更多
    private var hasMore = true


    suspend fun fetchArticle() {
        var res = articleService.list(pageOffset, pageSize)
        //delay(1000)
        if (res.code == 0 && res.data != null) {
            list1.add("")
            var tempList = mutableListOf<ArticleEntity>()
            if (pageOffset != 1) {
                tempList.addAll(list)
            }
            tempList.addAll(res.data!!)
            list = tempList
            listLoaded = true
            hasMore = pageOffset < 10
        } else {
            val massage = res.message
            pageOffset--
            if (pageOffset <= 1) {
                pageOffset = 1
            }
        }

        refreshing = false
    }

    /**
     * 下拉刷新
     */
    suspend fun refresh() {
        pageOffset = 1
        refreshing = true
        fetchArticle()
    }

    /**
     * 加载更多
     */
    suspend fun loadMore() {
        if (hasMore) {
            pageOffset++
            fetchArticle()
        }
    }

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

    private var articleEntity: ArticleEntity? = null
    var content by mutableStateOf(
        """
        $htmlHeader
        ${articleEntity?.content ?: ""}
        $htmlFooter
    """.trimIndent()
    )


    suspend fun fetchInfo() {
        val res = articleService.info("0")
        if (res.code == 0 && res.data != null) {
            articleEntity = res.data
            content =
                """
        $htmlHeader
        ${articleEntity?.content ?: ""}
        $htmlFooter
    """.trimIndent()
            infoLoaded = true
        } else {
            var massage = res.message
        }

    }
}