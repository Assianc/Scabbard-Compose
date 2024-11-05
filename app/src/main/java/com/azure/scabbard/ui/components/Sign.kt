package com.azure.scabbard.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.azure.scabbard.R

fun Modifier.unread(show: Boolean, color: Color): Modifier = this.drawWithContent {
    drawContent()
    if (show) {
        drawCircle(
            color = color,
            5.dp.toPx(),
            Offset(size.width - 5.dp.toPx(), 5.dp.toPx())
        )
    }
}

@Composable
fun AddBrowserIcon(imageResId: Int) {
    Box {
        // 显示主图像
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .clip(RoundedCornerShape(4.dp))
        )


        Image(
            painter = painterResource(id = R.drawable.ic_browse),
            contentDescription = null,
            modifier = Modifier
                .size(12.dp)
                .align(Alignment.BottomEnd)
                .offset(x = 3.dp, y = 3.dp)
        )
    }
}


@Preview
@Composable
private fun UnreadPreview() {
    Image(
        painter = painterResource(id = R.mipmap.developer_bx),
        contentDescription = "test",
        modifier = Modifier
            .unread(true, Color.Red)
            .padding(4.dp)
            .size(48.dp)
            .clip(RoundedCornerShape(4.dp))
    )
}

@Preview
@Composable
private fun BrowserPreview() {
    AddBrowserIcon(imageResId = R.mipmap.developer_bx)
}