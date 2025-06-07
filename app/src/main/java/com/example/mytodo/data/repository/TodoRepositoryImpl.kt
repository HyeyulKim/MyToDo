package com.example.mytodo.data.repository

import androidx.paging.*
import com.example.mytodo.data.local.TodoDao
import com.example.mytodo.data.local.entity.toDomainModel
import com.example.mytodo.data.local.entity.toEntity
import com.example.mytodo.domain.model.Todo
import com.example.mytodo.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
//TodoRepository 인터페이스 구현체
//Room Dao 사용
class TodoRepositoryImpl @Inject constructor(
    private val todoDao: TodoDao
) : TodoRepository {

    override fun getTodos(): Flow<PagingData<Todo>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            pagingSourceFactory = { todoDao.getTodosPagingSource() }
        ).flow
            .map { pagingData ->
                pagingData.map { entity ->
                    entity.toDomainModel()
                }
            }
    }

    override suspend fun addTodo(todo: Todo) {
        todoDao.insert(todo.toEntity())
    }

    override suspend fun deleteTodo(todo: Todo) {
        todoDao.delete(todo.toEntity())
    }

    override suspend fun getTodoById(id: Int): Todo? {
        return todoDao.getTodoById(id)?.toDomainModel()
    }

    override suspend fun updateTodo(todo: Todo) {
        todoDao.update(todo.toEntity())
    }
}
