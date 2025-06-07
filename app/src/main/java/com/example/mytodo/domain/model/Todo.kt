package com.example.mytodo.domain.model
//Domain Layer 모델
data class Todo(
    val id: Int = 0,
    val title: String,
    val isDone: Boolean,
    val dueDate: String? = null
)
