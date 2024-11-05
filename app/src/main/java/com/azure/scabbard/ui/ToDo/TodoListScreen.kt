package com.azure.scabbard.ui.ToDo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.Color
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun TodoListScreen(viewModel: TodoViewModel = viewModel()) {
    var newTodoContent by remember { mutableStateOf("") }
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()) // 时间格式

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("待办事项", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))

        // 显示待办事项列表
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(viewModel.todos.size) { index ->
                val todo = viewModel.todos[index]
                val formattedTime = "上次修改: ${dateFormat.format(Date(todo.timestamp))}" // 添加“上次修改”

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(todo.content)
                        Text(
                            text = formattedTime,
                            color = Color.Gray,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    Checkbox(
                        checked = todo.isDone,
                        onCheckedChange = { viewModel.toggleTodoDone(todo.id) }
                    )
                    IconButton(onClick = { viewModel.deleteTodo(todo.id) }) {
                        Icon(Icons.Default.Delete, contentDescription = "删除")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 输入框和添加按钮
        OutlinedTextField(
            value = newTodoContent,
            onValueChange = { newTodoContent = it },
            placeholder = { Text("输入待办内容") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                if (newTodoContent.isNotBlank()) {
                    viewModel.addTodo(newTodoContent)
                    newTodoContent = ""
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("添加待办")
        }
    }
}
