package com.compose.walleapp.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * @author  walle
 * @date    2022/9/28
 * @desc    .
 */

@Composable
fun ChartView(points:List<Double>,modifier: Modifier= Modifier) {

    //画布宽度 = 屏幕宽度 - padding*2
    val canvasWidth = LocalConfiguration.current.screenWidthDp - 8 * 2

    //每一行的的高度 dp
    val heightForRow = 24

    //总行数
    val countForRow = 5

    //小圆圈半径 dp
    val circleRadius = 2.5

    //画布高度 行高 * 总行数  + 小圆圈直径
    val canvasHeight = heightForRow * countForRow + circleRadius *2

    //1积分的高度dp 行高 * 行数 / 最大积分
    val perY = 8 // 24 * 5 /15  每8dp代表1积分

    //7平分的宽度
    val averageOfWidth = canvasWidth/7


    Canvas(
        modifier = modifier.size(
            width = canvasWidth.dp,
            height = canvasHeight.dp
        )
    ) {
        //画背景横线
        for (index in 0 .. countForRow) {
            //行高 * index + 小圆圈半径
            val y = (index * heightForRow + circleRadius).dp.toPx()
            drawLine(
                color = Color(0xffeeeeee),
                start = Offset(0f,y),
                end = Offset(size.width,y),
                strokeWidth = 2.5f
            )
        }

        //画圆圈,折线

        for (index in points.indices){
            val circleCenter = Offset(
                x =  (averageOfWidth*index + averageOfWidth/2).dp.toPx(),
                y =  (heightForRow* countForRow - points[index]*perY + circleRadius).dp.toPx()
            )
            drawCircle(
                color = Color(0xff149ee7),
                radius = circleRadius.dp.toPx(),
                center =    circleCenter,
                style = Stroke(width = 5f)
            )

            if(index < points.size-1) {
                val nextPointOffset = Offset(
                    x =  (averageOfWidth*(index+1) + averageOfWidth/2).dp.toPx(),
                    y =  (heightForRow* countForRow - points[index+1]*perY + circleRadius).dp.toPx()
                )
                drawLine(
                    color= Color(0xff149ee7),
                    start = circleCenter,
                    end =nextPointOffset,
                    strokeWidth = 5f
                )
            }
        }
    }
}
