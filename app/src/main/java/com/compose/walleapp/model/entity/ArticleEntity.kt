package com.compose.walleapp.model.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @author  walle
 * @date    2022/9/27
 * @desc    .
 */
@JsonClass(generateAdapter = true)
data class ArticleEntity(
    val title: String,
    val source: String,
    @Json(name = "time")
    val timestamp: String,
    val content:String?=""
)

data class ArticleEntityResponse(val data:List<ArticleEntity>?):BaseResponse()
data class ArticleInfoResponse(val data:ArticleEntity?):BaseResponse()


