package com.compose.walleapp.model

import com.compose.walleapp.model.service.HomeService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object Network {
    //文档地址：https://docs.apipost.cn/preview/1a28e17fa3c8f473/16838456ae6dc5c7
    //mock 数据请求 url
    //private const val baseUrl = "https://console-mock.apipost.cn/mock/e0070aba-781e-48a0-8d06-9c2adb7280c1/"
    private const val baseUrl = "https://mock.apifox.cn/m1/1979205-0-default/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
            )
        ).build()

    fun <T> createService(clazz: Class<T>):T{
       return retrofit.create(clazz)
    }

}