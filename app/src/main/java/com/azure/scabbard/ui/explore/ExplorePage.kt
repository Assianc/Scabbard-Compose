package com.azure.scabbard.ui.explore

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.azure.scabbard.R
import com.azure.scabbard.ui.TopBar
import com.azure.scabbard.ui.components.AddBrowserIcon
import com.azure.scabbard.ui.components.WebPage
import com.azure.scabbard.ui.components.openURL

@Composable
fun ExplorePage(navController: NavHostController) {
    val menuItems = listOf(
        MenuItemData(
            R.mipmap.ic_launcher,
            "官网",
            false,
            onClick = { navController.navigate("web") }),
        MenuItemData(R.mipmap.nextchat, "NextChat", true, onClick = {
            navController.navigate("NextChat")
        }),
        MenuItemData(R.mipmap.newapi, "NewAPI", true, onClick = {
            navController.navigate("NewAPI")
        }),
    )
    Column(
        Modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .fillMaxSize()
    ) {
        TopBar(title = "发现")
        // 菜单项
        menuItems.forEach { menuItem ->
            MenuItem(
                menuItem.iconRes,
                menuItem.title,
                menuItem.blank,
                onClick = menuItem.onClick
            )
        }
    }
}

@Composable
fun ExploreNavGraph(navController: NavHostController) {
    val context = LocalContext.current
    NavHost(
        navController = navController,
        startDestination = "Explore"
    ) {
        composable("Explore") { ExplorePage(navController) }
        composable("web") {
            WebPage("官网", "https://web.xbxin.com", navController)
        }
        composable("NextChat") {
            openURL(context, "https://next.bxin.top")
        }
        composable("NewAPI") {
            openURL(context, "https://llm.bxin.top")
        }
    }
}

data class MenuItemData(
    @DrawableRes val iconRes: Int,
    val title: String,
    val blank: Boolean,
    val onClick: () -> Unit
)

@Composable
fun MenuItem(
    @DrawableRes iconRes: Int,
    title: String,
    blank: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(start = 16.dp, end = 16.dp, top = 12.dp) // 设置上下左右的边距
            .clip(RoundedCornerShape(8.dp)) // 圆角
            .background(MaterialTheme.colorScheme.background)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.width(16.dp))
        if (blank) {
            AddBrowserIcon(iconRes)
        } else {
            Image(
                painter = painterResource(iconRes),
                contentDescription = title,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Icon(
            painter = painterResource(R.drawable.ic_right),
            contentDescription = "进入",
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ExplorePagePreview() {
    val navController = rememberNavController()
    ExplorePage(navController)
}
