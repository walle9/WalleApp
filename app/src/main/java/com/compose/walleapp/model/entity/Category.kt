package com.compose.walleapp.model.entity

/**
 * @author  walle
 * @date    2022/9/26
 * @desc    分类
 * @param title 分类标题
 * @param id
 *
 */
data class Category(
    val title: String,
    val id: String
)

/**
 *CategoryResponse
 * @param data
 */
data class CategoryResponse(var data:List<Category>):BaseResponse()
