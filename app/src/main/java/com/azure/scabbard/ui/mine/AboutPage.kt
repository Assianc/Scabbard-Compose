package com.azure.scabbard.ui.mine

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.azure.scabbard.BuildConfig
import com.azure.scabbard.R
import com.azure.scabbard.ui.TopBar
import androidx.compose.foundation.clickable
import com.azure.scabbard.ui.components.openURL

@Composable
fun AboutPage(navController: NavHostController) {

    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
    ) {
        TopBar(title = "关于") {
            navController.popBackStack()
        }
        Column(modifier = Modifier.padding(16.dp)) {
            Spacer(modifier = Modifier.height(24.dp))

            // App icon
            Image(
                painter = painterResource(R.mipmap.ic_launcher),
                contentDescription = "Scabbard",
                modifier = Modifier
                    .size(80.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Scabbard",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            val versionName = BuildConfig.VERSION_NAME
            val versionCode = BuildConfig.VERSION_CODE
            val versionText = stringResource(id = R.string.version_info, versionName, versionCode)

            Text(
                text = versionText,
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // App information
            Text(
                text = "What's this",
                fontSize = 14.sp,
                color = Color.Gray,
            )
            Text(
                text = "Scabbard Android",
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 8.dp)
            )

            HorizontalDivider(
                color = Color.Gray,
                thickness = 1.dp,
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
            )

            // developer
            Text(
                text = "Developers",
                fontSize = 14.sp,
                color = Color.Gray,
            )

            val devItems = listOf(
                DevItemData(
                    iconRes = R.mipmap.developer_bx,
                    username = "zhoubinxin",
                    desc = "Developer & Designer",
                    nickname = "bx"
                ),
                DevItemData(R.mipmap.developer_assianc, "Assianc", "Developer & Designer")
            )


            devItems.forEachIndexed { index, item ->
                DevItem(
                    iconRes = item.iconRes,
                    username = item.username,
                    desc = item.desc,
                    nickname = item.nickname.takeIf { !it.isNullOrEmpty() } ?: item.username
                )
            }
        }
    }

}


data class DevItemData(
    @DrawableRes val iconRes: Int,
    val username: String,
    val desc: String,
    val nickname: String? = null,
)

@Composable
fun DevItem(
    @DrawableRes iconRes: Int,
    nickname: String,
    username: String,
    desc: String,
) {
    val context = LocalContext.current // 获取当前上下文

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(top = 12.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.background)
            .clickable {
                openURL(context, "https://github.com/${username}")
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(16.dp))
        Image(
            painter = painterResource(iconRes),
            contentDescription = username,
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(24.dp))
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = nickname,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = desc,
                fontSize = 14.sp,
                color = Color.Gray,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AboutPagePreview() {
    val navController = rememberNavController()
    AboutPage(navController)
}