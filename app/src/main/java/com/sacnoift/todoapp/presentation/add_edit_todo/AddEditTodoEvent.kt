package com.sacnoift.todoapp.presentation.add_edit_todo

/**
 * Created by Patrick Adutwum on 28/12/2022.
 */
sealed class AddEditTodoEvent {
    data class OnTitleChange(val title: String): AddEditTodoEvent()
    data class OnDescriptionChange(val description: String): AddEditTodoEvent()
    object OnSaveTodoClick: AddEditTodoEvent()
}
