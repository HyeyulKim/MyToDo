package com.example.mytodo.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mytodo.presentation.ui.AddTodoScreen
import com.example.mytodo.presentation.ui.TodoDetailScreen
import com.example.mytodo.presentation.ui.TodoListScreen
import com.example.mytodo.presentation.viewmodel.TodoViewModel
//Compose Navigation Graph
//TodoList, AddTodo, TodoDetail 화면 연결
//Detail: todoId argument 전달
@Composable
fun TodoNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.TodoList.route
    ) {
        composable(Screen.TodoList.route) {
            val viewModel: TodoViewModel = hiltViewModel()
            TodoListScreen(
                viewModel = viewModel,
                onTodoClick = { todo ->
                    navController.navigate(Screen.TodoDetail.createRoute(todo.id))
                },
                onAddTodoClick = {
                    navController.navigate(Screen.AddTodo.route)
                }
            )
        }
        composable(Screen.AddTodo.route) {
            AddTodoScreen(onNavigateBack = { navController.popBackStack() })
        }
        composable(
            route = Screen.TodoDetail.route,
            arguments = listOf(navArgument("todoId") { type = NavType.IntType })
        ) { backStackEntry ->
            val todoId = backStackEntry.arguments?.getInt("todoId") ?: -1
            TodoDetailScreen(
                todoId = todoId,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
