package com.azure.scabbard.ui.Note

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class NoteViewModel : ViewModel() {
    private val _notes = mutableStateListOf<NoteItem>()
    val notes: List<NoteItem> = _notes

    fun addNote(content: String) {
        _notes.add(
            NoteItem(
                id = _notes.size,
                content = content,
                timestamp = System.currentTimeMillis()
            )
        )
    }

    fun deleteNote(id: Int) {
        _notes.removeIf { it.id == id }
    }
}
