package com.compose.walleapp.model.service

import com.compose.walleapp.model.Network
import com.compose.walleapp.model.entity.ArticleEntityResponse
import com.compose.walleapp.model.entity.VideoEntityResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface VideoService {

    @GET("video/list")
    suspend fun list(@Query("pageOffset")pageOffset:Int,@Query("pageSize")pageSize:Int): VideoEntityResponse

    companion object{
        fun instance():VideoService{
            return Network.createService(VideoService::class.java)
        }
    }
}