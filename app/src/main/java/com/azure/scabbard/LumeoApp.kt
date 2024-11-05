package com.azure.scabbard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.azure.scabbard.ui.HomePage
import com.azure.scabbard.ui.NavBar
import com.azure.scabbard.ui.mine.MineNavGraph
import com.azure.scabbard.ui.theme.LumeoTheme
import kotlinx.coroutines.launch
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.azure.scabbard.ui.TaskAndNoteScreen
import com.azure.scabbard.ui.explore.ExploreNavGraph


class LumeoApp : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LumeoContent()
        }
    }
}

@Composable
fun LumeoContent() {
    val viewModel: LumeoViewModel = viewModel()
    val theme = when {
        viewModel.isAutoTheme.value -> isSystemInDarkTheme()
        else -> viewModel.theme.value == LumeoTheme.Theme.Dark
    }

    LumeoTheme(theme) {
        Column {
            val pagerState = rememberPagerState(pageCount = { 4 })

            val exploreNavController = rememberNavController()
            val mineNavController = rememberNavController()

            HorizontalPager(
                state = pagerState,
                userScrollEnabled = false,
                pageSpacing = 16.dp,
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                pageContent = { page ->
                    when (page) {
                        0 -> HomePage()
                        1 -> TaskAndNoteScreen() // 第二个界面支持待办和记事本切换
                        2 -> ExploreNavGraph(exploreNavController)
                        3 -> MineNavGraph(mineNavController)
                    }
                }
            )

            val coroutineScope = rememberCoroutineScope()

            // 处理导航栏的点击
            NavBar(pagerState.currentPage) { page ->
                coroutineScope.launch {
                    pagerState.animateScrollToPage(page)
                }
            }
        }
    }
}
