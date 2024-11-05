package com.azure.scabbard.ui.Note

data class NoteItem(
    val id: Int,
    val content: String,
//    val title: String,
    val timestamp: Long = System.currentTimeMillis() // 添加时间戳字段
)
