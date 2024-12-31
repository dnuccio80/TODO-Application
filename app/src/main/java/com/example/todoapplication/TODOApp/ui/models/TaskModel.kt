package com.example.todoapplication.TODOApp.ui.models

data class TaskModel(
    val description: String,
    val category: CategoryModel,
    val isDone: Boolean = false
)
