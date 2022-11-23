package com.compose.walleapp.model.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @author  walle
 * @date    2022/9/26
 * @desc    .
 */
@JsonClass(generateAdapter = true)
data class SwiperEntity(
    @Json(name = "imgUrl") val imageUrl: String, val title: String?=""
)

data class SwiperResponse(val data:List<SwiperEntity>?):BaseResponse()