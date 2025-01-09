package com.example.todoapplication.TODOApp.domain.tasksUseCases

import com.example.todoapplication.TODOApp.data.TaskRepository
import javax.inject.Inject


class GetAllTasksUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    operator fun invoke() = taskRepository.getAllTasks()
}