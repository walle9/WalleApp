package com.compose.walleapp.model.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.Duration

/**
 * @author  walle
 * @date    2022/9/27
 * @desc    .
 */
@JsonClass(generateAdapter = true)
data class VideoEntity(
    val title: String,
    val type: String,
    val duration: String,
    @Json(name = "cover")
    val imageUrl:String
)

data class VideoEntityResponse(val data:List<VideoEntity>?):BaseResponse()
