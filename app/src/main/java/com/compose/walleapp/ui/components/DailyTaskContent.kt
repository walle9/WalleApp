package com.compose.walleapp.ui.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Help
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * @author  walle
 * @date    2022/9/28
 * @desc    .
 */

@Composable
fun DailyTaskContent() {
    DailyTaskItem(
        "登录",
        "5积分/每日首次登录",
        "已获5积分/每日上限5积分",
        1f
    )

    DailyTaskItem(
        "文章学习",
        "10积分/每有效阅读1篇文章",
        "已获50积分/每日上限100积分",
        0.7f
    )

    DailyTaskItem(
        "视听学习",
        "10积分/每有效观看视频或收听音频累计1分钟",
        "已获50积分/每日上限100积分",
        0.5f
    )
}

@Composable
fun DailyTaskItem(title: String, secondaryText: String, desc: String,percent:Float) {




    var inlineContentId = "inlineContentId"
    var secondaryAnnotatedText = buildAnnotatedString {
        append(secondaryText)
        appendInlineContent(inlineContentId,"icon")
    }

    var inlineContent = mapOf(

        pair = Pair(
            inlineContentId,
            InlineTextContent(
                placeholder = Placeholder(
                    width = 14.sp, height = 14.sp, placeholderVerticalAlign = PlaceholderVerticalAlign.AboveBaseline
                )
            ) {

                // it 就是上面的 "icon"

                Icon(
                    imageVector = Icons.Filled.Help,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        Log.i("===", "点击了问号")
                    }
                )
            }
        )
    )


    Row(
        modifier = Modifier.fillMaxWidth().padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(modifier = Modifier.weight(7.5f)) {
            Text(text = title, fontSize = 16.sp, color = Color(0xff333333))
            Text(
                text = secondaryAnnotatedText,
                inlineContent = inlineContent,
                fontSize = 14.sp,
                color = Color(0xff333333)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                LinearProgressIndicator(progress = percent, modifier = Modifier.weight(3f))
                Text(text = desc, fontSize = 10.sp, color = Color(0xff333333), modifier = Modifier.weight(7f, false).padding(start = 8.dp))
            }
        }

        OutlinedButton(
            modifier = Modifier.weight(2.5f),
            onClick = {},
            border = if(percent>=1f) ButtonDefaults.outlinedBorder else BorderStroke(1.dp, Color(0xffff5900)),
            shape = CircleShape,
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xffff5900)),
            enabled = percent<1f
        ) {
            Text(text = "去学习")
        }
    }
}

@Preview
@Composable
fun DailyTaskContentPreview() {
    DailyTaskContent()
}