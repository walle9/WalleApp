package com.compose.walleapp.model.service

import com.compose.walleapp.model.Network
import com.compose.walleapp.model.entity.UserInfoResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * @author  walle
 * @date    2022/11/29
 * @desc    .
 */
interface UserService {

    @POST("user/signIn")
    @FormUrlEncoded
    suspend fun signIn(@Field("userName")userName:String,@Field("password")password:String):UserInfoResponse

    companion object{
        fun instance():UserService{
            return Network.createService(UserService::class.java)
        }
    }
}