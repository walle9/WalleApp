package com.compose.walleapp.ui.components

import android.annotation.NonNull
import android.webkit.ValueCallback
import android.webkit.WebView
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withContext

/**
 * @author  walle
 * @date    2022/10/11
 * @desc    .
 */

@Composable
fun WebView(state: WebViewState) {
    AndroidView(factory = { context ->
        WebView(context).apply {
            with(settings) {
                javaScriptEnabled= true

            }
        }
    }) { view ->

        when (val content = state.content) {
            is WebContent.Url -> {
                val url = content.url
                if (url.isNotEmpty() && url != view.url) {
                    view.loadUrl(content.url)
                }
            }
            is WebContent.Data -> {
                view.loadDataWithBaseURL(content.baseUrl, content.data, null, "utf-8", null)
            }
        }
    }
}

sealed class WebContent {
    data class Url(val url: String) : WebContent()
    data class Data(val data: String, val baseUrl: String?=null) : WebContent()
}

class WebViewState(webContent: WebContent) {
    //网页内容 url或者data
    var content by mutableStateOf(webContent)

    // TODO: 遗留问题 
    var pageTitle: String? by mutableStateOf(null)

    //共享流的数据类型
    class Event()

    //共享流
    private val events:MutableSharedFlow<Event> = MutableSharedFlow()

    suspend fun WebView.handleEvent(): Unit = withContext(Dispatchers.Main){
        events.collect{event ->


        }
    }

    //执行js方法
    fun evaluateJavascript(script:String,resultCallback: ((String)->Unit)? ={}){

    }

}

@Composable
fun rememberWebViewState(url: String) = remember(key1 = url) {
    WebViewState(WebContent.Url(url))
}

@Composable
fun rememberWebViewState(data: String, baseUrl: String?=null) = remember(key1 = data, key2 = baseUrl) {
    WebViewState(WebContent.Data(data, baseUrl))
}

@Preview
@Composable
fun WebViewPreview() {
    WebView(rememberWebViewState(data = "<h1>我是一个网页</h1>"))
}