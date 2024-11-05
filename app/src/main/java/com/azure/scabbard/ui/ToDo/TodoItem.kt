package com.azure.scabbard.ui.ToDo

data class TodoItem(
    val id: Int,
    val content: String,
    val isDone: Boolean = false,
    val timestamp: Long = System.currentTimeMillis() // 添加时间戳字段
)
