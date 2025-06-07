package com.example.mytodo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.mytodo.presentation.ui.TodoListScreen
import com.example.mytodo.presentation.viewmodel.TodoViewModel
import com.example.mytodo.ui.theme.MyTodoTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.compose.rememberNavController
import com.example.mytodo.presentation.navigation.TodoNavGraph
//앱 진입점
//Hilt EntryPoint
//Compose Navigation 적용
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val todoViewModel: TodoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTodoTheme {
                val navController = rememberNavController()
                Surface(color = MaterialTheme.colorScheme.background) {
                    TodoNavGraph(navController = navController)
                }
            }
        }
    }
}

