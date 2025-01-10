package com.example.todoapplication.TODOApp.domain.tasksUseCases

import com.example.todoapplication.TODOApp.data.TaskRepository
import javax.inject.Inject

class DeleteTaskByCategoryIdUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    suspend operator fun invoke(categoryId: Int) = taskRepository.deleteTasksByCategoryId(categoryId)
}