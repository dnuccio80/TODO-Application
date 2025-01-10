package com.example.todoapplication.TODOApp.domain.tasksUseCases

import com.example.todoapplication.TODOApp.data.TaskDao
import com.example.todoapplication.TODOApp.data.TaskRepository
import javax.inject.Inject

class GetClickedTasksUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    operator fun invoke() = taskRepository.getClickedTasks()
}