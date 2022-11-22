package com.compose.walleapp.ui.navigation

/**
 * @author  walle
 * @date    2022/10/10
 * @desc    .
 */
sealed class Destinations(val route:String){
    //首页大框架
    object HomeFragment:Destinations("HomeFragment")
    //文章详情页
    object ArticleDetail:Destinations("ArticleDetail")
    //文章详情页
    object VideoDetail:Destinations("VideoDetail")
    //登录页
    object Login:Destinations("Login")
}
