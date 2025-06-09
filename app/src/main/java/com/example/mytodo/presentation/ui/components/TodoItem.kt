package com.example.mytodo.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mytodo.domain.model.Todo
//개별 아이템 UI
@Composable
fun TodoItem(
    todo: Todo,
    onClick: () -> Unit,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
                .padding(16.dp)
        ) {
            Checkbox(
                checked = todo.isDone,
                onCheckedChange = { isChecked -> onCheckedChange(isChecked)}
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = todo.title, style = MaterialTheme.typography.bodyLarge)
                todo.dueDate?.let {
                    Text(
                        text = "마감일: $it",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}
