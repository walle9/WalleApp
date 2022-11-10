package com.compose.walleapp.ui.components

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.AnimationScope
import androidx.compose.animation.slideOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.compose.walleapp.ui.navigation.Destinations
import com.compose.walleapp.ui.screens.ArticleDetailScreen
import com.compose.walleapp.ui.screens.MainFrame
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

/**
 * @author  walle
 * @date    2022/10/10
 * @desc    导航控制器
 */
@ExperimentalAnimationApi
@Composable
fun NavHostApp() {

    val navController = rememberAnimatedNavController()
    AnimatedNavHost(navController = navController, startDestination = Destinations.HomeFragment.route) {

        //首页大框架
        composable(route = Destinations.HomeFragment.route, enterTransition = {
            slideIntoContainer(AnimatedContentScope.SlideDirection.Right)
        }, exitTransition = {
            slideOutOfContainer(AnimatedContentScope.SlideDirection.Left)
        }) {
            MainFrame(onNavigateToArticle = {
                navController.navigate(Destinations.ArticleDetail.route)
            })
        }

        //文章详情页
        composable(route = Destinations.ArticleDetail.route,enterTransition = {
            slideIntoContainer(AnimatedContentScope.SlideDirection.Left)
        }, exitTransition = {
            slideOutOfContainer(AnimatedContentScope.SlideDirection.Right)
        }) {
            ArticleDetailScreen(onBack = {
                navController.popBackStack()
            })
        }

    }
}

@Preview
@Composable
fun NavHostAppPreview() {

}