package com.example.todoapplication.TODOApp.data

import com.example.todoapplication.TODOApp.ui.models.TaskModel
import com.example.todoapplication.TODOApp.ui.models.toTaskEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class TaskRepository @Inject constructor(private val taskDao: TaskDao) {

    fun getAllTasks(): Flow<List<TaskModel>> {
        return taskDao.getAllTasks().map { list ->
            list.map {
                it.toTaskModel()
            }
        }
    }

    fun getClickedTasks(): Flow<List<TaskModel>> {
        return taskDao.getClickedTasks().map { list ->
            list.map {
                it.toTaskModel()
            }
        }
    }

    suspend fun addTask(taskModel: TaskModel) {
        taskDao.addTask(taskModel.toTaskEntity())
    }

    suspend fun updateTask(taskModel: TaskModel) {
        taskDao.updateTask(taskModel.toTaskEntity())
    }

    suspend fun deleteTask(taskModel: TaskModel) {
        taskDao.deleteTask(taskModel.toTaskEntity())
    }

    suspend fun deleteTasksByCategoryId(categoryId: Int) {
        taskDao.deleteTaskByCategoryId(categoryId)
    }

}