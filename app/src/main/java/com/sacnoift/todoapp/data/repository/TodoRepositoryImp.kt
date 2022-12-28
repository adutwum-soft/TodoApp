package com.sacnoift.todoapp.data.repository

import com.sacnoift.todoapp.domain.repository.TodoRepository
import com.sacnoift.todoapp.data.local.TodoDao
import com.sacnoift.todoapp.data.local.TodoEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by Patrick Adutwum on 27/12/2022.
 */
class TodoRepositoryImp(
    private val dao: TodoDao
): TodoRepository {
    override suspend fun insertTodo(todo: TodoEntity) {
        dao.insertTodo(todo)
    }

    override suspend fun deleteTodo(id: Int) {
        dao.deleteTodo(id)
    }

    override suspend fun getTodoById(id: Int): TodoEntity? {
        return dao.getTodoById(id)
    }

    override fun getTodo(): Flow<List<TodoEntity>> {
         return dao.getTodo()
    }
}