package com.sacnoift.todoapp.presentation.add_edit_todo

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.sacnoift.todoapp.util.UiEvent

/**
 * Created by Patrick Adutwum on 28/12/2022.
 */

@Composable
fun AddEditTodoScreen(
    onPopBackStack: () -> Unit,
    viewModel: AddEditTodoViewModel = hiltViewModel()
){
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect{ event ->
            when(event){
                is UiEvent.PopBackStack -> onPopBackStack()
                is UiEvent.ShowSnackBar ->{
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                }
                else -> Unit
            }
        }
    }
    
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(AddEditTodoEvent.OnSaveTodoClick)
            }) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Save"
                )
            }
        }
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it)
        ) {
            TextField(
                value = viewModel.title,
                onValueChange = { title ->
                    viewModel.onEvent(AddEditTodoEvent.OnTitleChange(title))
                },
                placeholder = {
                    Text(text = "Title")
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = viewModel.description,
                onValueChange = { des ->
                    viewModel.onEvent(AddEditTodoEvent.OnDescriptionChange(des))
                },
                placeholder = {
                    Text(text = "Description")
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = false,
                maxLines = 6,
            )
        }
    }
}