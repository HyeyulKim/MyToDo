package com.example.mytodo.domain.usecase

import com.example.mytodo.domain.model.Todo
import com.example.mytodo.domain.repository.TodoRepository
import javax.inject.Inject


class GetTodoByIdUseCase @Inject constructor(
    private val repository: TodoRepository
) {
    suspend operator fun invoke(id: Int): Todo? {
        return repository.getTodoById(id)
    }
}
