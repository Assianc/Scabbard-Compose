package com.azure.scabbard.ui.components

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.azure.scabbard.ui.TopBar

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebViewScreen(url: String, navController: NavHostController) {
    var webView: WebView? by remember { mutableStateOf(null) }

    AndroidView(
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                settings.javaScriptCanOpenWindowsAutomatically = true
                settings.domStorageEnabled = true

                // 设置WebViewClient以处理返回操作
                webViewClient = object : WebViewClient() {
                    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                        return false // 让WebView加载URL
                    }
                }

                loadUrl(url)
                webView = this // 保存对WebView的引用
            }
        },
        modifier = Modifier.fillMaxSize()
    )

    // 返回操作
    BackHandler(enabled = webView != null) {
        webView?.let {
            if (it.canGoBack()) {
                it.goBack()
            } else {
                navController.popBackStack()
            }
        }
    }
}

@Composable
fun WebPage(title: String, url: String, navController: NavHostController) {
    Column {
        TopBar(title = title) {
            navController.popBackStack()
        }
        WebViewScreen(url, navController)
    }
}

@Preview
@Composable
private fun WebPagePreview() {
    val navController = rememberNavController()
    WebPage("百度", "https://www.baidu.com", navController)
}


@Preview
@Composable
private fun WebViewScreenPreview() {
    val navController = rememberNavController()
    WebViewScreen("https://www.baidu.com", navController)
}