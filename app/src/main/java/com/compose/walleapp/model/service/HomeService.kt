package com.compose.walleapp.model.service

import com.compose.walleapp.model.Network
import com.compose.walleapp.model.entity.CategoryResponse
import com.compose.walleapp.model.entity.SwiperEntity
import com.compose.walleapp.model.entity.SwiperResponse
import retrofit2.http.GET

interface HomeService {

    @GET("category/list")
    suspend fun category(): CategoryResponse

    @GET("recommand/banner")
    suspend fun banner(): SwiperResponse

    companion object{
        fun instance():HomeService{
            return Network.createService(HomeService::class.java)
        }
    }
}