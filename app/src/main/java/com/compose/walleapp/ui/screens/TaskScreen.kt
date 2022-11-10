package com.compose.walleapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.*
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.compose.walleapp.ui.components.ChartView
import com.compose.walleapp.ui.components.CircleRing
import com.compose.walleapp.ui.components.DailyTaskContent
import com.compose.walleapp.ui.components.appBarHeight
import com.compose.walleapp.viewmodel.TaskViewModel

/**
 * @author  walle
 * @date    2022/9/23
 * @desc    .
 */

@Preview
@Composable
fun TaskScreen(taskViewModel: TaskViewModel = viewModel()) {

    //圆环高度
    var boxWidthDp: Int
    with(LocalConfiguration.current) {
        boxWidthDp = screenWidthDp / 2
    }

    //当学年积分改变时,重新计算百分比
    LaunchedEffect(key1 = taskViewModel.pointOfYear) {
        taskViewModel.updatePointPercent()
        taskViewModel.updateTips()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(Color(0xff149ee7), Color.White)
                )
            )
    ) {

        /*//系统TopAppBar
        TopAppBar(
            title = {
                Text(
                    text = "学习任务",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.White,
                )
            },
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
            modifier = Modifier.statusBarsPadding()
        )*/

        //标题栏
        Row(
            modifier = Modifier
                .statusBarsPadding()
                .height(appBarHeight),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "学习任务",
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                color = Color.White
            )
        }


        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //学习周期
            item {
                Text(
                    text = taskViewModel.taskDate,
                    fontSize = 12.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
            //学习进度
            item {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .height(boxWidthDp.dp)
                        .padding(top = 8.dp)
                ) {
                    //圆环
                    CircleRing(boxWidthDp, taskViewModel)

                    //进度数据
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = buildAnnotatedString {
                                append(taskViewModel.pointOfYear.toString())
                                withStyle(SpanStyle(fontSize = 12.sp)) {
                                    append("分")
                                }
                            },
                            fontSize = 36.sp,
                            color = Color.White
                        )

                        Text(
                            text = "学年积分",
                            fontSize = 12.sp,
                            color = Color.White
                        )

                    }
                }
            }
            //积分显示
            item {
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-40).dp)
                ) {
                    Column {
                        Text(
                            text = "${taskViewModel.totalPointOfYear}分",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                        Text(
                            text = "学年规定积分",
                            fontSize = 12.sp,
                            color = Color.White
                        )
                    }

                    Column {
                        Text(
                            text = "${taskViewModel.totalPointOfYear - taskViewModel.pointOfYear}分",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                        Text(
                            text = "还差",
                            fontSize = 12.sp,
                            color = Color.White
                        )
                    }
                }
            }
            //学习明细
            item {
                Column(
                    modifier = Modifier
                        .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                        .background(Color.White)
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    Text(
                        text = "学习明细",
                        fontSize = 16.sp,
                        color = Color(0xff333333)
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "最近一周获取积分情况",
                        fontSize = 14.sp,
                        color = Color(0xff999999)
                    )

                    //积分折线图
                    ChartView(taskViewModel.pointOfWeek, modifier = Modifier.padding(vertical = 8.dp))
                    //日期
                    Row {
                        taskViewModel.weeks.forEach {
                            Text(
                                text = it,
                                fontSize = 12.sp,
                                color = Color(0xff999999),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                    //今日任务提醒
                    Text(
                        text = taskViewModel.tips,
                        fontSize = 14.sp,
                        color = Color(0xff149ee7),
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .background(
                                color = Color(0x33149ee7),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(16.dp)
                            .fillMaxSize()
                    )
                }
            }

            //每日任务
            item {
                DailyTaskContent()
            }

        }
    }
}

@Preview
@Composable
fun TaskScreenPreview() {
    TaskScreen()
}