package com.compose.walleapp.model.service

import com.compose.walleapp.model.Network
import com.compose.walleapp.model.entity.ArticleEntityResponse
import com.compose.walleapp.model.entity.VideoEntityResponse
import com.compose.walleapp.model.entity.VideoInfoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface VideoService {

    @GET("video/list")
    suspend fun list(@Query("pageOffset")pageOffset:Int,@Query("pageSize")pageSize:Int): VideoEntityResponse

    @GET("video/info")
    suspend fun videoInfo(@Query("id")id:String):VideoInfoResponse

    companion object{
        fun instance():VideoService{
            return Network.createService(VideoService::class.java)
        }
    }
}