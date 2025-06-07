package com.example.mytodo.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.mytodo.domain.model.Todo
import com.example.mytodo.presentation.viewmodel.TodoViewModel
import com.example.mytodo.presentation.ui.components.TodoItem
import com.example.mytodo.presentation.ui.components.LoadingIndicator
import com.example.mytodo.presentation.ui.components.ErrorItem
//리스트 UI
//Paging 연동
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(
    viewModel: TodoViewModel,
    onTodoClick: (Todo) -> Unit,
    onAddTodoClick: () -> Unit
) {
    val todoPagingItems = viewModel.todoPagingItems.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("할 일 목록") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddTodoClick) {
                Icon(Icons.Default.Add, contentDescription = "할 일 추가")
            }
        }
    ) { paddingValues ->
        TodoListContent(
            todos = todoPagingItems,
            onTodoClick = onTodoClick,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
fun TodoListContent(
    todos: LazyPagingItems<Todo>,
    onTodoClick: (Todo) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(todos.itemCount) { index ->
            todos[index]?.let { todo ->
                TodoItem(todo = todo, onClick = { onTodoClick(todo) })
            }
        }

        todos.apply {
            when {
                loadState.refresh is androidx.paging.LoadState.Loading -> {
                    item { LoadingIndicator() }
                }
                loadState.append is androidx.paging.LoadState.Loading -> {
                    item { LoadingIndicator() }
                }
                loadState.refresh is androidx.paging.LoadState.Error -> {
                    val e = todos.loadState.refresh as androidx.paging.LoadState.Error
                    item { ErrorItem(message = e.error.localizedMessage ?: "오류 발생") }
                }
                loadState.append is androidx.paging.LoadState.Error -> {
                    val e = todos.loadState.append as androidx.paging.LoadState.Error
                    item { ErrorItem(message = e.error.localizedMessage ?: "오류 발생") }
                }
            }
        }
    }
}
