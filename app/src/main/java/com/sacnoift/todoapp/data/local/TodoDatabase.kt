package com.sacnoift.todoapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by Patrick Adutwum on 27/12/2022.
 */

@Database(
    entities = [TodoEntity::class],
    version = 1
)
abstract class TodoDatabase: RoomDatabase() {
    abstract val dao: TodoDao
}