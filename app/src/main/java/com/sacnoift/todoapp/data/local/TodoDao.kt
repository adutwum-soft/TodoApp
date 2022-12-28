package com.sacnoift.todoapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

/**
 * Created by Patrick Adutwum on 27/12/2022.
 */

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTodo(todo: TodoEntity)

    @Query("DELETE FROM TodoEntity WHERE id=:id")
    suspend fun deleteTodo(id: Int)

    @Query("SELECT * FROM TodoEntity WHERE id =:id")
    suspend fun getTodoById(id: Int): TodoEntity?

    @Query("SELECT * FROM TodoEntity")
    fun getTodo(): Flow<List<TodoEntity>>
}