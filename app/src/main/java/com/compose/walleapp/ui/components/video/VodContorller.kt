package com.compose.walleapp.ui.components.video

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.tencent.rtmp.TXVodPlayer

/**
 * @author  walle
 * @date    2022/11/15
 * @desc    .
 */
class VodContorller(context: Context) {

    val vodPlayer = TXVodPlayer(context)

    fun startPlay(url: String) {
        vodPlayer.startVodPlay(url)
    }

}

@Composable
fun rememberVodController(context: Context = LocalContext.current) = remember {
    VodContorller(context)
}