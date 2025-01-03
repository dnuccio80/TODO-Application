package com.example.todoapplication.TODOApp.ui.models

import com.example.todoapplication.TODOApp.data.TaskEntity

data class TaskModel(
    val id: Int = 0,
    val description: String,
    val category: CategoryModel,
    val isDone: Boolean = false
)

fun TaskModel.toTaskEntity() = TaskEntity(id, description, category, isDone)