package com.compose.walleapp.model.entity

data class UserInfoEntity(val username:String)
data class UserInfoResponse(val data:UserInfoEntity?):BaseResponse()
