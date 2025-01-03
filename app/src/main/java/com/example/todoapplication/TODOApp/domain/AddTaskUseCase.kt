package com.example.todoapplication.TODOApp.domain

import com.example.todoapplication.TODOApp.data.TaskRepository
import com.example.todoapplication.TODOApp.ui.models.TaskModel
import javax.inject.Inject

class AddTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    suspend operator fun invoke(task:TaskModel) = taskRepository.addTask(task)
}