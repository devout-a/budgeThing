package com.budgething.ui.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Dropdown(
    itemsList: List<String>,
    selectedItem: String,
    label: String,
    onSelect: (String) -> Unit,
    onRemove: () -> Unit
) {
    var showOptions by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clickable { showOptions = true }
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(20.dp)
                )
                .width(170.dp)
                .height(50.dp)
        ) {
            Text(selectedItem.ifEmpty { label }, color = MaterialTheme.colorScheme.onSurface)

            if (selectedItem.isNotEmpty()) {
                IconButton(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    onClick = onRemove
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Remove",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }

        }

        DropdownMenu(
            expanded = showOptions,
            onDismissRequest = { showOptions = false },
            modifier = Modifier
                .width(170.dp)
                .heightIn(max = 200.dp)
        ) {
            itemsList.forEach { item ->
                DropdownMenuItem(
                    text = { Text(item) },
                    onClick = {
                        onSelect(item)
                        showOptions = false
                    }
                )
            }
        }
    }
}