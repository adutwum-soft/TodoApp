package com.sacnoift.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sacnoift.todoapp.presentation.add_edit_todo.AddEditTodoScreen
import com.sacnoift.todoapp.presentation.todo_list.TodoListScreen
import com.sacnoift.todoapp.ui.theme.TodoAppTheme
import com.sacnoift.todoapp.util.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Routes.TODO_LIST,
                    ){
                        composable(Routes.TODO_LIST){
                            TodoListScreen(
                                onNavigate = {
                                    navController.navigate(it.route)
                                }
                            )
                        }
                        composable(
                            Routes.ADD_EDIT_TODO + "?todoId={todoId}",
                            arguments = listOf(
                                navArgument(name = "todoId"){
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ){
                            AddEditTodoScreen(onPopBackStack = {
                                navController.popBackStack()
                            })
                        }
                    }
//                    DestinationsNavHost(navGraph = NavGraphs.root)
                }
            }
        }
    }
}