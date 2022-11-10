package com.compose.walleapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.compose.walleapp.model.entity.ArticleEntity
import com.compose.walleapp.model.entity.VideoEntity

/**
 * @author  walle
 * @date    2022/9/27
 * @desc    .
 */

class TaskViewModel : ViewModel() {

    //
    var taskDate by mutableStateOf("学习周期:2022.01.01-2022.12.31")
        private set


    //学年总积分
    var totalPointOfYear = 13500

    //学年积分
    var pointOfYear by mutableStateOf(10000)
        private set

    //学年积分进度 = 220f * pointOfYear/totalPointOfYear
    var pointOfYearPercent by mutableStateOf(0f)
        private set

    /**
     * 更新学年进度
     */
    fun updatePointPercent() {
        pointOfYearPercent = 220f * pointOfYear / totalPointOfYear
    }

    //一周积分情况
    var pointOfWeek by mutableStateOf(listOf(0.0, 2.0, 6.0, 9.5, 10.0, 15.0, 5.0))
        private set

    //日期
    val weeks = listOf("09.22", "09.23", "09.24", "09.25", "09.26", "09.27", "今天")

    //今日积分
    private var todayPoint = 13

    //今日提醒文字
    var tips = "今日获得0积分,快去完成下面任务吧"
        private set

    /**
     * 更新任务提醒文字
     */
    fun updateTips() {

        tips = if (todayPoint < 15) {
            "今日获得${todayPoint}积分,快去完成下面任务吧"
        } else {
            "今日获得${todayPoint}积分,已完成任务"
        }
    }

}