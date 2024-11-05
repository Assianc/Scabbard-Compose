package com.azure.scabbard.ui.mine

import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

@Composable
fun MinePage(navController: NavHostController) {
    val menuItems = listOf(
        MenuItemData(R.drawable.ic_about, "关于", onClick = { navController.navigate("about") }),
        MenuItemData(R.drawable.ic_fire, "获取更新", onClick = {
            Toast.makeText(navController.context, "开发中", Toast.LENGTH_SHORT).show()
        }),
        MenuItemData(R.drawable.ic_docs, "帮助文档", onClick = {
            Toast.makeText(navController.context, "开发中", Toast.LENGTH_SHORT).show()
        }),
        MenuItemData(R.drawable.ic_flower, "外观", onClick = {
            Toast.makeText(navController.context, "开发中", Toast.LENGTH_SHORT).show()
        })
    )
    Column(
        Modifier
            .background(MaterialTheme.colorScheme.primaryContainer)
            .fillMaxSize()
    ) {
        TopBar(title = "我的")
        // 菜单项
        menuItems.forEach { menuItem ->
            MenuItem(
                menuItem.iconRes,
                menuItem.title,
                onClick = menuItem.onClick
            )
        }
    }
}

@Composable
fun MineNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "mine"
    ) {
        composable("mine") { MinePage(navController) }
        composable("about") { AboutPage(navController) }
        composable("update") {
            Toast.makeText(navController.context, "开发中", Toast.LENGTH_SHORT).show()
        }
        composable("help") {
            Toast.makeText(navController.context, "开发中", Toast.LENGTH_SHORT).show()
        }
    }
}

data class MenuItemData(
    @DrawableRes val iconRes: Int,
    val title: String,
    val onClick: () -> Unit
)

@Composable
fun MenuItem(
    @DrawableRes iconRes: Int,
    title: String,
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
        Icon(
            painter = painterResource(iconRes),
            contentDescription = title,
            modifier = Modifier.size(24.dp)
        )
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
fun MinePagePreview() {
    val navController = rememberNavController()
    MinePage(navController)
}
