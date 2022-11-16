package com.compose.walleapp.ui.components.video

import android.annotation.SuppressLint
import android.view.LayoutInflater
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.compose.walleapp.R
import com.tencent.liteav.txcplayer.common.VodPlayerControl
import com.tencent.liteav.txcvodplayer.TXCVodVideoView
import com.tencent.rtmp.TXVodPlayer
import com.tencent.rtmp.ui.TXCloudVideoView

/**
 * @author  walle
 * @date    2022/11/15
 * @desc    .
 */

@SuppressLint("InflateParams")
@Composable
fun VideoView(vodPlayer: TXVodPlayer) {
    AndroidView(factory = { context ->
        /*(LayoutInflater.from(context).inflate(R.layout.video, null, false)
            .findViewById(R.id.videoView) as TXCloudVideoView).apply {
                vodPlayer.setPlayerView(this)
        }*/

        TXCloudVideoView(context).apply {
            vodPlayer.setPlayerView(this)
        }
    })
}