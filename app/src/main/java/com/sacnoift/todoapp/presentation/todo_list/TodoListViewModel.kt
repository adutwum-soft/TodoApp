package com.sacnoift.todoapp.presentation.todo_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sacnoift.todoapp.data.local.TodoEntity
import com.sacnoift.todoapp.domain.repository.TodoRepository
import com.sacnoift.todoapp.util.Routes
import com.sacnoift.todoapp.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Patrick Adutwum on 27/12/2022.
 */

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val repository: TodoRepository
): ViewModel() {
    val todos = repository.getTodo()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var deletedTodo: TodoEntity? = null

    fun onEvent(event: TodoListEvent){
        when(event){
            is TodoListEvent.OnTodoClick ->{
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO + "?todoId=${event.todo.id}"))
            }
            is TodoListEvent.OnDeleteTodoClick ->{
                viewModelScope.launch {
                    deletedTodo = event.todo
                    event.todo.id?.let { repository.deleteTodo(it) }
                    sendUiEvent(UiEvent.ShowSnackBar(
                        message = "Todo deleted",
                        action = "Undo"
                    ))
                }
            }
            is TodoListEvent.UndoDeleteClick ->{
                deletedTodo?.let {
                    viewModelScope.launch {
                        repository.insertTodo(it)
                    }
                }
            }
            is TodoListEvent.OnAddTodoClick ->{
                sendUiEvent(UiEvent.Navigate(Routes.ADD_EDIT_TODO))
            }
            is TodoListEvent.OnDoneChanged ->{
                viewModelScope.launch {
                    repository.insertTodo(
                        event.todo.copy(
                            isDone = event.isDone
                        )
                    )
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}