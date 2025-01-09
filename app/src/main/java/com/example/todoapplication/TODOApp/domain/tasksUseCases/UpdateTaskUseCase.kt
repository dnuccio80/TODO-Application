package com.example.todoapplication.TODOApp.domain.tasksUseCases

import com.example.todoapplication.TODOApp.data.TaskRepository
import com.example.todoapplication.TODOApp.ui.models.TaskModel
import javax.inject.Inject

class UpdateTaskUseCase @Inject constructor(private val taskRepository: TaskRepository){
    suspend operator fun invoke(task:TaskModel) = taskRepository.updateTask(task)
}