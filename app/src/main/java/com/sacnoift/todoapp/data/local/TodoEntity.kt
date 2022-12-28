package com.sacnoift.todoapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Patrick Adutwum on 27/12/2022.
 */
@Entity
data class TodoEntity(
    val title: String,
    val description: String?,
    val isDone: Boolean,
    @PrimaryKey val id: Int? = null,
)
