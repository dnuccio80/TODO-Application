package com.example.todoapplication.TODOApp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.todoapplication.TODOApp.data.categories.CategoryDao
import com.example.todoapplication.TODOApp.data.categories.CategoryEntity
import com.example.todoapplication.TODOApp.data.di.Converters

@Database(entities = [TaskEntity::class, CategoryEntity::class], version = 5)
@TypeConverters(Converters::class)
abstract class TodoDataBase: RoomDatabase() {
    abstract val taskDao:TaskDao
    abstract val categoryDao:CategoryDao
}