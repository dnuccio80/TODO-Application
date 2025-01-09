package com.example.todoapplication.TODOApp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todoapplication.TODOApp.data.categories.CategoryEntity
import com.example.todoapplication.TODOApp.data.categories.toCategoryModel
import com.example.todoapplication.TODOApp.ui.models.CategoryModel
import com.example.todoapplication.TODOApp.ui.models.TaskModel

@Entity
data class TaskEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val description: String,
    val category: CategoryModel,
    val isDone: Boolean = false
)

fun TaskEntity.toTaskModel() = TaskModel(id, description, category, isDone)