package com.example.mytodo.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mytodo.presentation.viewmodel.TodoViewModel
//상세화면 UI
//삭제 버튼 추가 (TopAppBar actions)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoDetailScreen(
    todoId: Int,
    viewModel: TodoViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    val todo = viewModel.selectedTodo.value
    var title by remember { mutableStateOf("") }

    LaunchedEffect(todoId) {
        viewModel.loadTodoById(todoId)
    }

    LaunchedEffect(todo) {
        title = todo?.title ?: ""
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("할 일 상세") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "뒤로가기")
                    }
                },
                actions = {
                    if (todo != null) {
                        IconButton(onClick = {
                            viewModel.deleteTodo(todo)
                            onNavigateBack()
                        }) {
                            Icon(Icons.Default.Delete, contentDescription = "삭제")
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            if (todo != null) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("제목") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        viewModel.updateTodo(todo.copy(title = title))
                        onNavigateBack()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("수정 완료")
                }
            } else {
                Text("할 일을 불러오는 중...")
            }
        }
    }
}