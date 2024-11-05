package com.azure.scabbard.ui.ToDo

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class TodoViewModel : ViewModel() {
    private val _todos = mutableStateListOf<TodoItem>()
    val todos: List<TodoItem> = _todos

    fun addTodo(content: String) {
        _todos.add(
            TodoItem(
                id = _todos.size,
                content = content,
                timestamp = System.currentTimeMillis()
            )
        )
    }

    fun toggleTodoDone(id: Int) {
        _todos.find { it.id == id }?.let {
            _todos[_todos.indexOf(it)] = it.copy(isDone = !it.isDone)
        }
    }

    fun deleteTodo(id: Int) {
        _todos.removeIf { it.id == id }
    }
}

