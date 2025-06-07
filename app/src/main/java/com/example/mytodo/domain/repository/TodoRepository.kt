package com.example.mytodo.domain.repository

import androidx.paging.PagingData
import com.example.mytodo.domain.model.Todo
import kotlinx.coroutines.flow.Flow
//Domain Layer 인터페이스
interface TodoRepository {
    fun getTodos(): Flow<PagingData<Todo>>
    suspend fun getTodoById(id: Int): Todo?
    suspend fun addTodo(todo: Todo)
    suspend fun deleteTodo(todo: Todo)
    suspend fun updateTodo(todo: Todo)
}
