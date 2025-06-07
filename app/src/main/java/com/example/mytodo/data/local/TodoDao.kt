package com.example.mytodo.data.local

import androidx.paging.PagingSource
import androidx.room.*
import com.example.mytodo.data.local.entity.TodoEntity
//Room DAO 인터페이스
//주요 역할: DB 액세스 (PagingSource 포함)
@Dao
interface TodoDao {
    @Query("SELECT * FROM todos WHERE id = :todoId LIMIT 1")
    suspend fun getTodoById(todoId: Int): TodoEntity?

    @Query("SELECT * FROM todos ORDER BY id DESC")
    fun getTodosPagingSource(): PagingSource<Int, TodoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: TodoEntity)

    @Delete
    suspend fun delete(todo: TodoEntity)

    @Update
    suspend fun update(todo: TodoEntity)
}
