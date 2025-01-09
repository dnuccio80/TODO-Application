package com.example.todoapplication.TODOApp.data.di

import android.app.Application
import androidx.room.Room
import com.example.todoapplication.TODOApp.data.TodoDataBase
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
    fun provideDataBase(app: Application): TodoDataBase {
        return Room.databaseBuilder(
            app,
            TodoDataBase::class.java,
            "MyDataBase"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideTaskDao(dataBase: TodoDataBase) = dataBase.taskDao

    @Provides
    @Singleton
    fun provideCategoryDao(dataBase: TodoDataBase) = dataBase.categoryDao

}