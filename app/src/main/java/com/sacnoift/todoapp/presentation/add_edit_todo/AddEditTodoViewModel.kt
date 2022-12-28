package com.sacnoift.todoapp.presentation.add_edit_todo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sacnoift.todoapp.data.local.TodoEntity
import com.sacnoift.todoapp.domain.repository.TodoRepository
import com.sacnoift.todoapp.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Patrick Adutwum on 28/12/2022.
 */

@HiltViewModel
class AddEditTodoViewModel @Inject constructor(
    private val repository: TodoRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    var todo by mutableStateOf<TodoEntity?>(null)
        private set

    var title by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        val todoId = savedStateHandle.get<Int>("todoId")
        if (todoId != -1){
            viewModelScope.launch {
                todoId?.let {
                    repository.getTodoById(it)?.let { todoIt ->
                        title = todoIt.title
                        description = todoIt.description ?: ""
                        this@AddEditTodoViewModel.todo = todoIt
                    }
                }
            }
        }
    }

    fun onEvent(event: AddEditTodoEvent){
        when(event){
            is AddEditTodoEvent.OnTitleChange -> {
                title = event.title
            }
            is AddEditTodoEvent.OnSaveTodoClick -> {
                viewModelScope.launch {
                    if (title.isBlank()){
                        sendUiEvent(UiEvent.ShowSnackBar(
                            message = "The title can't be empty"
                        ))
                        return@launch
                    }
                    repository.insertTodo(
                        TodoEntity(
                            title = title,
                            description = description,
                            isDone = todo?.isDone ?: false,
                            id = todo?.id
                        )
                    )
                    sendUiEvent(UiEvent.PopBackStack)
                }
            }
            is AddEditTodoEvent.OnDescriptionChange -> {
                description = event.description
            }
        }
    }

    private fun sendUiEvent(event: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}