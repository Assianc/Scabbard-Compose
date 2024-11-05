package com.azure.scabbard.ui.components

import android.content.Context
import android.content.Intent
import android.net.Uri

// 打开 URL
fun openURL(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
}
