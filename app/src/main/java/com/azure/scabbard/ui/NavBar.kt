package com.azure.scabbard.ui

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.azure.scabbard.R
import com.azure.scabbard.ui.theme.LumeoTheme

@Composable
fun NavBar(selected: Int, onSelectChange: (Int) -> Unit) {
    val navItems = listOf(
        NavItemData(R.drawable.ic_home, "首页"),
        NavItemData(R.drawable.ic_edit, "小记"),
        NavItemData(R.drawable.ic_focus, "发现"),
        NavItemData(R.drawable.ic_me, "我")
    )

    Row(
        Modifier
            .background(MaterialTheme.colorScheme.background)
            .height(84.dp)
            .padding(16.dp, 0.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        navItems.forEachIndexed { index, navItem ->
            NavItem(
                iconRes = navItem.iconRes,
                title = navItem.title,
                tint = if (selected == index) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary,
                isSelected = (selected == index),
                onClick = { onSelectChange(index) }
            )
        }

    }
}

data class NavItemData(
    @DrawableRes val iconRes: Int,
    val title: String
)


@Composable
fun RowScope.NavItem(
    @DrawableRes iconRes: Int,
    title: String,
    tint: Color,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .weight(1f)
            .fillMaxHeight(),
        shape = RectangleShape,
        colors = ButtonDefaults.outlinedButtonColors()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(iconRes),
                contentDescription = title,
                modifier = Modifier.size(24.dp),
                tint = tint
            )
            // 使用 AnimatedVisibility 控制文字显示
            AnimatedVisibility(visible = isSelected) {
                Text(
                    text = title,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    color = tint
                )
            }
        }
    }
}


@Preview
@Composable
fun NavBarPreview() {
    var selectedTab = remember { mutableIntStateOf(0) }
    NavBar(selectedTab.intValue) {
        selectedTab.intValue = it
    }
}

@Preview
@Composable
fun NavBarDarkPreview() {
    var selectedTab = remember { mutableIntStateOf(0) }
    LumeoTheme(darkTheme = true) {
        NavBar(selectedTab.intValue) {
            selectedTab.intValue = it
        }
    }
}
