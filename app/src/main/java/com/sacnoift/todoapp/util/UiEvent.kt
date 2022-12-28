package com.sacnoift.todoapp.util

/**
 * Created by Patrick Adutwum on 27/12/2022.
 */
sealed class UiEvent {

    object PopBackStack: UiEvent()

    data class Navigate(val route: String): UiEvent()

    data class ShowSnackBar(
        val message: String,
        val action: String? = null
    ): UiEvent()
}
