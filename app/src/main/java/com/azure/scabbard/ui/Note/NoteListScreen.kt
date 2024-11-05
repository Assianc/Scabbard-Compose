package com.azure.scabbard.ui.Note

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Preview
@Composable
fun NoteListScreen(viewModel: NoteViewModel = viewModel()) {
    var newNoteContent by remember { mutableStateOf("") }
    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()) // 时间格式

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        Text("记事本", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(viewModel.notes.size) { index ->
                val note = viewModel.notes[index]
                val formattedTime = "上次修改: ${dateFormat.format(Date(note.timestamp))}" // 添加“上次修改”
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(note.content)
                        Text(
                            text = formattedTime,
                            color = Color.Gray,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                    Text(note.content, modifier = Modifier.weight(1f))
                    IconButton(onClick = { viewModel.deleteNote(note.id) }) {
                        Icon(Icons.Default.Delete, contentDescription = "删除")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = newNoteContent,
            onValueChange = { newNoteContent = it },
            placeholder = { Text("输入记事内容") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                if (newNoteContent.isNotBlank()) {
                    viewModel.addNote(newNoteContent)
                    newNoteContent = ""
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("添加记事")
        }
    }
}
