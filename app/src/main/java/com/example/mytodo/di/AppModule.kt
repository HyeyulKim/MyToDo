package com.example.mytodo.di

import android.content.Context
import androidx.room.Room
import com.example.mytodo.data.local.TodoDao
import com.example.mytodo.data.local.TodoDatabase
import com.example.mytodo.data.repository.TodoRepositoryImpl
import com.example.mytodo.domain.repository.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
//Hilt 모듈: Room, Dao, Repository 제공
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTodoDatabase(@ApplicationContext appContext: Context): TodoDatabase {
        return Room.databaseBuilder(
            appContext,
            TodoDatabase::class.java,
            "todo_db"
        )
            .addMigrations(TodoDatabase.MIGRATION_1_2) // ✅ Migration 추가
            .build()
    }

    @Provides
    fun provideTodoDao(database: TodoDatabase): TodoDao {
        return database.todoDao()
    }

    @Provides
    @Singleton
    fun provideTodoRepository(todoDao: TodoDao): TodoRepository {
        return TodoRepositoryImpl(todoDao)
    }
}
