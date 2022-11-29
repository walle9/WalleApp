package com.compose.walleapp.ui.components

import android.provider.SyncStateContract.Constants
import android.widget.ImageView.ScaleType
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.compose.walleapp.model.entity.ArticleEntity
import com.compose.walleapp.model.entity.VideoEntity
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer

/**
 * @author  walle
 * @date    2022/9/27
 * @desc    .
 */
@Composable
fun VideoItem(video: VideoEntity,loaded: Boolean,modifier: Modifier = Modifier) {


    val constraintSet = ConstraintSet {
        val cover = createRefFor("cover")
        val title = createRefFor("title")
        val type = createRefFor("type")
        val duration = createRefFor("duration")
        val divider = createRefFor("divider")

        constrain(cover){
            start.linkTo(parent.start)
            top.linkTo(parent.top)
            width = Dimension.value(115.5.dp)
        }
        constrain(title){
            start.linkTo(cover.end, margin = 8.dp)
            top.linkTo(parent.top)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
        }

        constrain(type){
            start.linkTo(title.start)
            bottom.linkTo(cover.bottom)
        }

        constrain(duration){
            end.linkTo(parent.end)
            bottom.linkTo(type.bottom)
        }

        constrain(divider){
            top.linkTo(cover.bottom, margin = 8.dp)
        }
    }

    ConstraintLayout(constraintSet, modifier = modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        AsyncImage(
            model = video.imageUrl,
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .layoutId("cover")
                .aspectRatio(16 / 9f)
                .clip(RoundedCornerShape(8.dp))
                .placeholder(visible = !loaded, highlight = PlaceholderHighlight.shimmer())
        )
        Text(
            text = video.title,
            color = Color(0xff333333),
            fontSize = 16.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.layoutId("title")
                .placeholder(visible = !loaded, highlight = PlaceholderHighlight.shimmer())
        )
        Text(
            text = video.type?:"",
            color = Color(0xff999999),
            fontSize = 10.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.layoutId("type")
                .placeholder(visible = !loaded, highlight = PlaceholderHighlight.shimmer())
        )
        Text(
            text = "时长:${video.duration}",
            color = Color(0xff999999),
            fontSize = 10.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.layoutId("duration")
                .placeholder(visible = !loaded, highlight = PlaceholderHighlight.shimmer())
        )

        Divider(modifier = Modifier
            .layoutId("divider")
            .padding(vertical = 8.dp))
    }
}