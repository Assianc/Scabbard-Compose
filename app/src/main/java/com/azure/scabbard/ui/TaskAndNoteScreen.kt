package com.azure.scabbard.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.azure.scabbard.ui.Note.NoteListScreen
import com.azure.scabbard.ui.Note.NoteViewModel
import com.azure.scabbard.ui.ToDo.TodoListScreen
import com.azure.scabbard.ui.ToDo.TodoViewModel

// 待办和记事本切换
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TaskAndNoteScreen(
    todoViewModel: TodoViewModel = viewModel(),
    noteViewModel: NoteViewModel = viewModel()
) {
    var isTodoScreen by remember { mutableStateOf(true) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            ToggleButton(
                isSelected = isTodoScreen,
                icon = Icons.Default.Star, // 待办图标
                text = "待办事项",
                onClick = { isTodoScreen = true }
            )

            ToggleButton(
                isSelected = !isTodoScreen,
                icon = Icons.Default.MailOutline, // 记事本图标
                text = "记事本",
                onClick = { isTodoScreen = false }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 切换动画效果
        AnimatedContent(
            targetState = isTodoScreen,
            transitionSpec = {
                fadeIn(animationSpec = tween(300)) with fadeOut(animationSpec = tween(300))
            }
        ) { targetState ->
            if (targetState) {
                TodoListScreen(viewModel = todoViewModel)
            } else {
                NoteListScreen(viewModel = noteViewModel)
            }
        }
    }
}

@Composable
fun ToggleButton(
    isSelected: Boolean,
    icon: ImageVector,
    text: String,
    onClick: () -> Unit,
) {
    val buttonBackgroundColor by animateColorAsState(
        targetValue = if (isSelected) Color(0xFFFFA500) else Color.Transparent, // 选中状态为橙色
        animationSpec = tween(300), label = "ButtonBackgroundColorAnimation"
    )
    val contentColor = if (isSelected) Color.White else Color.Gray
    val shape: Shape = if (isSelected) MaterialTheme.shapes.medium else RectangleShape

    Row(
        modifier = Modifier
            .clip(shape)
            .background(buttonBackgroundColor)
            .padding(8.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(imageVector = icon, contentDescription = null, tint = contentColor)
        AnimatedVisibility(visible = isSelected) {
            Text(text, color = contentColor)
        }
    }
}
