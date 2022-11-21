package com.compose.walleapp.ui.components.video

import android.content.Context
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import com.tencent.rtmp.ITXVodPlayListener
import com.tencent.rtmp.TXLiveConstants
import com.tencent.rtmp.TXVodPlayer

/**
 * @author  walle
 * @date    2022/11/15
 * @desc    .
 */
class VodController(
    context: Context,
    val playerValue: PlayerValue
) {

    val vodPlayer = TXVodPlayer(context).apply {
        setVodListener(object : ITXVodPlayListener {
            override fun onPlayEvent(player: TXVodPlayer?, event: Int, param: Bundle?) {
                when (event) {

                    TXLiveConstants.PLAY_EVT_PLAY_LOADING -> {
                        playerValue.state = PlayState.LOADING
                    }

                    TXLiveConstants.PLAY_EVT_VOD_PLAY_PREPARED,
                    TXLiveConstants.PLAY_EVT_RCV_FIRST_I_FRAME,
                    TXLiveConstants.PLAY_EVT_VOD_LOADING_END,
                    TXLiveConstants.PLAY_EVT_PLAY_BEGIN -> {
                        playerValue.state = PlayState.PLAYING
                    }

                    //获取视频时长和进度
                    TXLiveConstants.PLAY_EVT_PLAY_PROGRESS -> {
                        playerValue.duration =
                            param?.getInt(TXLiveConstants.EVT_PLAY_DURATION_MS)?.toLong() ?: 0L

                        playerValue.currentPosition =
                            param?.getInt(TXLiveConstants.EVT_PLAY_PROGRESS_MS)?.toLong() ?: 0L
                    }
                }
            }

            override fun onNetStatus(p0: TXVodPlayer?, p1: Bundle?) {

            }

        })
    }

    /**
     * 开始播放
     */
    fun startPlay() {
        playerValue.vodeoUrl = playerValue.vodeoUrl
        vodPlayer.startVodPlay(playerValue.vodeoUrl)
    }

    /**
     * 配置改变后重新播放
     */
    fun restore(){vodPlayer.setStartTime(playerValue.currentPosition/1000f)
        startPlay()
    }

    /**
     * 停止播放
     */
    fun stopPlay() {
        vodPlayer.stopPlay(true)
    }

    /**
     * 暂停播放
     */
    fun pause() {
        vodPlayer.pause()
        playerValue.state = PlayState.PAUSE
    }

    /**
     * 恢复播放
     */
    fun resume() {
        vodPlayer.resume()
    }

    /**
     * 设置播放进度
     *
     */
    fun seekTo(millSeconds: Long) {
        vodPlayer.seek((millSeconds / 1000).toInt())
    }

}

@Composable
fun rememberVodController(
    context: Context = LocalContext.current,
    videoUrl: String,
    coverUrl: String? = "",
    title: String? = "",
) = rememberSaveable(saver = object : Saver<VodController, PlayerValue> {
    override fun restore(value: PlayerValue): VodController? {
        return VodController(context, value)
    }

    override fun SaverScope.save(value: VodController): PlayerValue {
        return value.playerValue
    }
}) {
    val playerValue = PlayerValue()
    playerValue.vodeoUrl = videoUrl
    playerValue.coverUrl = coverUrl?:""
    playerValue.title = title?:""
    VodController(context, playerValue)
}
/*
= remember {
    VodController(context,coverUrl,videoUrl,title)
}*/
