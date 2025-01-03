package com.example.todoapplication.TODOApp.data.di

import android.app.Application
import androidx.room.Room
import com.example.todoapplication.TODOApp.data.TaskDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModules {

    @Provides
    @Singleton
    fun provideDataBase(app: Application) : TaskDataBase {
        return Room.databaseBuilder(
            app,
            TaskDataBase::class.java,
            "TaskDataBase"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTaskDao(dataBase: TaskDataBase) = dataBase.taskDao

}