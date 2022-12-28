package com.sacnoift.todoapp.domain.repository

import com.sacnoift.todoapp.data.local.TodoEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by Patrick Adutwum on 27/12/2022.
 */

interface TodoRepository {

    suspend fun insertTodo(todo: TodoEntity)

    suspend fun deleteTodo(id: Int)

    suspend fun getTodoById(id: Int): TodoEntity?

    fun getTodo(): Flow<List<TodoEntity>>
}