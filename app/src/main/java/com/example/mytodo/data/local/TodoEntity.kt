package com.example.mytodo.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mytodo.domain.model.Todo

//Room Entity
//Domain Model 변환 함수 포함
@Entity(tableName = "todos")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val isDone: Boolean,
    val dueDate: String? = null
)

// 확장 함수: TodoEntity → to do
fun TodoEntity.toDomainModel(): Todo {
    return Todo(
        id = id,
        title = title,
        isDone = isDone,
        dueDate = dueDate
    )
}

// 확장 함수: to do → TodoEntity
fun Todo.toEntity(): TodoEntity {
    return TodoEntity(
        id = id,
        title = title,
        isDone = isDone,
        dueDate = dueDate
    )
}
