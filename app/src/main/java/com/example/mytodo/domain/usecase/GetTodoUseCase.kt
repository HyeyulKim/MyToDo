package com.example.mytodo.domain.usecase

import androidx.paging.PagingData
import com.example.mytodo.domain.model.Todo
import com.example.mytodo.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetTodosUseCase @Inject constructor(
    private val repository: TodoRepository
) {
    operator fun invoke(): Flow<PagingData<Todo>> {
        return repository.getTodos()
    }
}
