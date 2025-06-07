package com.example.mytodo.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.mytodo.domain.model.Todo
import com.example.mytodo.domain.usecase.AddTodoUseCase
import com.example.mytodo.domain.usecase.DeleteTodoUseCase
import com.example.mytodo.domain.usecase.GetTodoByIdUseCase
import com.example.mytodo.domain.usecase.GetTodosUseCase
import com.example.mytodo.domain.usecase.UpdateTodoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject
//ViewModel에서 PagingData Flow, Add/Delete/Detail 조회 관리
@HiltViewModel
class TodoViewModel @Inject constructor(
    private val getTodosUseCase: GetTodosUseCase,
    private val addTodoUseCase: AddTodoUseCase,
    private val deleteTodoUseCase: DeleteTodoUseCase,
    private val getTodoByIdUseCase: GetTodoByIdUseCase,
    private val updateTodoUseCase: UpdateTodoUseCase
) : ViewModel() {

    val todoPagingItems: Flow<PagingData<Todo>> = getTodosUseCase()
        .cachedIn(viewModelScope)

    fun addTodo(todo: Todo) {
        viewModelScope.launch {
            addTodoUseCase(todo)
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            deleteTodoUseCase(todo)
        }
    }

    private val _selectedTodo = mutableStateOf<Todo?>(null)
    val selectedTodo: State<Todo?> = _selectedTodo

    fun loadTodoById(todoId: Int) {
        viewModelScope.launch {
            _selectedTodo.value = getTodoByIdUseCase(todoId)
        }
    }

    fun updateTodo(todo: Todo) {
        viewModelScope.launch {
            updateTodoUseCase(todo)
        }
    }
}

