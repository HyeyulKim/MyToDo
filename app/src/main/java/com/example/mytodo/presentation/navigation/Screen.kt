package com.example.mytodo.presentation.navigation
//Route 관리용 sealed class
sealed class Screen(val route: String) {
    object TodoList : Screen("todo_list")
    object AddTodo : Screen("add_todo")
    object TodoDetail : Screen("todo_detail/{todoId}") {
        fun createRoute(todoId: Int): String = "todo_detail/$todoId"
    }
}