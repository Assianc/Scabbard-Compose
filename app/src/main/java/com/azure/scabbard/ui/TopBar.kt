package com.azure.scabbard.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.azure.scabbard.R

@Composable
fun TopBar(title: String, onBack: (() -> Unit)? = null) {
    Box(
        Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxWidth()
    ) {
        Row(
            Modifier
                .height(48.dp)
        ) {
            if (onBack != null) {
                Icon(
                    painterResource(R.drawable.ic_back),
                    null,
                    Modifier
                        .clickable(onClick = onBack)
                        .align(Alignment.CenterVertically)
                        .size(36.dp)
                        .padding(8.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(Modifier.weight(1f))
            // val viewModel: LumeoViewModel = viewModel()
            // Icon(
            //     painterResource(R.drawable.ic_edit),
            //     "切换主题",
            //     Modifier
            //         .clickable {
            //             viewModel.theme.value = when (viewModel.theme.value) {
            //                 LumeoTheme.Theme.Light -> LumeoTheme.Theme.Dark
            //                 LumeoTheme.Theme.Dark -> LumeoTheme.Theme.Light
            //             }
            //             viewModel.isAutoTheme.value = false
            //         }
            //         .align(Alignment.CenterVertically)
            //         .size(36.dp)
            //         .padding(8.dp),
            //     tint = MaterialTheme.colorScheme.primary
            // )
        }
        Text(title, Modifier.align(Alignment.Center))
    }
}

@Preview
@Composable
private fun TopBarPreview() {
    TopBar("标题")
    
}