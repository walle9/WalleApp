package com.compose.walleapp

import android.app.Application
import android.util.Log
import com.tencent.rtmp.TXLiveBase
import com.tencent.rtmp.TXLiveBaseListener


/**
 * @author  walle
 * @date    2022/11/15
 * @desc    .
 */
class App:Application() {

    override fun onCreate() {
        super.onCreate()

        val licenceURL = "https://license.vod2.myqcloud.com/license/v2/1256566258_1/v_cube.license" // 获取到的 licence url

        val licenceKey = "6293a9c464ce678f4dd3d8e6535873b8" // 获取到的 licence key

        TXLiveBase.getInstance().setLicence(this, licenceURL, licenceKey)
        TXLiveBase.setListener(object : TXLiveBaseListener() {
            override fun onLicenceLoaded(result: Int, reason: String) {
                Log.i("TAG", "onLicenceLoaded: result:$result, reason:$reason")
            }
        })
    }
}