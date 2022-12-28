package com.sacnoift.todoapp.presentation.todo_list

import com.sacnoift.todoapp.data.local.TodoEntity

/**
 * Created by Patrick Adutwum on 28/12/2022.
 */

sealed class TodoListEvent {

    data class OnDeleteTodoClick(val todo: TodoEntity): TodoListEvent()

    data class OnDoneChanged(val todo: TodoEntity, val isDone: Boolean): TodoListEvent()

    object UndoDeleteClick: TodoListEvent()

    data class OnTodoClick(val todo: TodoEntity): TodoListEvent()

    object OnAddTodoClick: TodoListEvent()
}
