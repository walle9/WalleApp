package com.compose.walleapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.walleapp.model.entity.ArticleEntity

/**
 * @author  walle
 * @date    2022/9/27
 * @desc    文章列表item
 */
@Composable
fun ArticleItem(article: ArticleEntity,modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(8.dp)) {
        Text(
            text = article.title,
            color = Color(0xff333333),
            fontSize = 16.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "来源${article.source}",
                fontSize = 10.sp,
                color = Color(0xff999999),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = article.timestamp,
                fontSize = 10.sp,
                color = Color(0xff999999),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        
        Spacer(modifier = Modifier.height(8.dp))

        Divider()
    }
}

@Preview
@Composable
fun ArticleItemPreview() {
}