package com.example.todoapplication.TODOApp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.todoapplication.TODOApp.data.di.Converters

@Database(entities = [TaskEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class TaskDataBase : RoomDatabase() {
    abstract val taskDao: TaskDao
}