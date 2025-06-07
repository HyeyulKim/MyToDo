package com.example.mytodo.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mytodo.domain.model.Todo
import com.example.mytodo.presentation.viewmodel.TodoViewModel
//할 일 추가 UI
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTodoScreen(
    viewModel: TodoViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    var title by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("할 일 추가") })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (title.isNotBlank()) {
                        viewModel.addTodo(Todo(title = title, isDone = false))
                        onNavigateBack()
                    }
                }
            ) {
                Icon(Icons.Default.Check, contentDescription = "저장")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("할 일 제목") },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
