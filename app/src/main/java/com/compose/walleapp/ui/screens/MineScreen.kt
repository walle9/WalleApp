package com.compose.walleapp.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.compose.walleapp.R
import com.compose.walleapp.ui.components.TopAppBar

/**
 * @author  walle
 * @date    2022/9/23
 * @desc    .
 */

@Preview
@Composable
fun MineScreen() {
    val menus = listOf(
        menuItem(R.mipmap.points, "学习积分"),
        menuItem(R.mipmap.browser_history, "浏览记录"),
        menuItem(R.mipmap.archives, "学习档案"),
        menuItem(R.mipmap.questions, "常见问题"),
        menuItem(R.mipmap.version, "版本信息"),
        menuItem(R.mipmap.settings, "个人设置"),
    )
    Column {
        TopAppBar {
            Text(text = "我的", fontSize = 18.sp, color = Color.White)
        }

        LazyColumn {
            //头像部分
            item {
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 24.dp, horizontal = 8.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_avatar),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(62.dp)
                            .clip(CircleShape)
                    )

                    Column(
                        verticalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .height(62.dp)
                    ) {
                        Text(text = "未登录", fontSize = 18.sp, color = Color(0xff333333))
                        Text(text = "已坚持学习0天", fontSize = 12.sp, color = Color(0xff999999))
                    }

                }
            }
            //菜单部分

            itemsIndexed(menus) { index, item ->
                
                if (index ==2) {
                    Spacer(modifier = Modifier.height(8.dp).fillMaxWidth().background(color= Color(0xfff5f5f5)))
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = null,
                        modifier = Modifier.size(17.dp),
                        tint = Color(0xff149ee7)
                    )

                    Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(vertical = 8.dp)
                        ) {
                            Text(
                                text = item.title,
                                color = Color(0xff333333),
                                fontSize = 16.sp,
                                modifier = Modifier.weight(1f)
                            )
                            Icon(
                                imageVector = Icons.Default.ArrowForwardIos,
                                tint = Color.Gray,
                                contentDescription = null,
                                modifier = Modifier.size(13.dp)
                            )

                        }
                        if (index!=1) {
                            Divider()
                        }

                    }

                }
            }


        }
    }

}

data class menuItem(@DrawableRes val icon: Int, val title: String)

@Preview
@Composable
fun MineScreenPreview() {
    TaskScreen()
}