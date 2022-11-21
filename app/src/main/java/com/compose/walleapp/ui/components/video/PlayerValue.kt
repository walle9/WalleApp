package com.compose.walleapp.ui.components.video

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/**
 * @author  walle
 * @date    2022/11/18
 * @desc    播放器相关数据类
 */
class PlayerValue {

    //视频总时长
    var duration by mutableStateOf(0L)

    //当前播放进度
    var currentPosition by mutableStateOf(0L)

    //当前状态

    var state by mutableStateOf(PlayState.NONE)

}

/**
 * 播放状态
 */
enum class PlayState{
    NONE,       //未播放
    LOADING,    //加载中
    PLAYING,    //播放中
    PAUSE,      //已暂停
}