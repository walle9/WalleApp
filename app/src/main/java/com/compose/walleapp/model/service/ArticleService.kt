package com.compose.walleapp.model.service

import com.compose.walleapp.model.Network
import com.compose.walleapp.model.entity.ArticleEntityResponse
import com.compose.walleapp.model.entity.ArticleInfoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleService {

    @GET("article/list")
    suspend fun list(@Query("pageOffset")pageOffset:Int,@Query("pageSize")pageSize:Int): ArticleEntityResponse

    @GET("article/info")
    suspend fun info(@Query("id")id:String): ArticleInfoResponse

    companion object{
        fun instance():ArticleService{
            return Network.createService(ArticleService::class.java)
        }
    }
}