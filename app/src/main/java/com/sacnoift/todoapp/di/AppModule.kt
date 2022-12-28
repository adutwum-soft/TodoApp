package com.sacnoift.todoapp.di

import android.app.Application
import androidx.room.Room
import com.sacnoift.todoapp.domain.repository.TodoRepository
import com.sacnoift.todoapp.data.local.TodoDatabase
import com.sacnoift.todoapp.data.repository.TodoRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Patrick Adutwum on 27/12/2022.
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTodoDatabase(app: Application): TodoDatabase{
        return Room.databaseBuilder(
         app,
         TodoDatabase::class.java,
         "tododb.db"
        ).build()
    }

    @Provides
    @Singleton
    fun providesTodoRepository(db: TodoDatabase): TodoRepository {
        return TodoRepositoryImp(db.dao)
    }
}