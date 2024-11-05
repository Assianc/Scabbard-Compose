package com.azure.scabbard

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.azure.scabbard.ui.theme.LumeoTheme


class LumeoViewModel : ViewModel() {
    var theme = mutableStateOf(LumeoTheme.Theme.Light)
    var isAutoTheme = mutableStateOf(true)
}
