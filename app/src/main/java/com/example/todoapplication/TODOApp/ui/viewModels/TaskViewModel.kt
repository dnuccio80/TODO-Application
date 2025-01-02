package com.example.todoapplication.TODOApp.ui.viewModels

import androidx.lifecycle.ViewModel
import com.example.todoapplication.TODOApp.ui.models.CategoryModel
import com.example.todoapplication.TODOApp.ui.models.TaskModel
import com.example.todoapplication.TODOApp.ui.models.PredetermineCategories
import com.example.todoapplication.ui.theme.BusinessColor
import com.example.todoapplication.ui.theme.OtherColor
import com.example.todoapplication.ui.theme.PersonalColor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor() : ViewModel() {

    // Categories

    private var categoriesList = listOf(
        CategoryModel("Personal", PersonalColor),
        CategoryModel("Business", BusinessColor),
        CategoryModel("Other", OtherColor),
    )


    private val _categories: MutableStateFlow<List<CategoryModel>> = MutableStateFlow(categoriesList)
    val categories: StateFlow<List<CategoryModel>> = _categories

    fun updateCategory(category: CategoryModel) {
        val updatedList = categoriesList.map {
            if(it == category){
                it.copy(isClicked = !it.isClicked)
            } else {
                it
            }
        }
        categoriesList = updatedList
        _categories.value = categoriesList
        filterTasks()
    }

    private fun filterTasks() {
        val filteredCategoryList = categoriesList.filter { it.isClicked }
        val filteredTaskList = tasksList.filter {
            filteredCategoryList.contains(it.category)
        }
        _tasks.value = filteredTaskList

    }

    // Tasks

    private var tasksList = listOf(
        TaskModel("Task 1 test", PredetermineCategories.Personal),
        TaskModel("Task 2", PredetermineCategories.Business),
        TaskModel("Task 3", PredetermineCategories.Other),
    )

    private val _tasks: MutableStateFlow<List<TaskModel>> = MutableStateFlow(tasksList)
    val tasks: StateFlow<List<TaskModel>> = _tasks

    fun addTask(task:TaskModel){
        tasksList += task
        _tasks.value = tasksList
    }

    fun updateTask(task:TaskModel){
        val updateList = tasksList.map {
            if(it == task){
                it.copy(isDone = !it.isDone)
            } else {
                it
            }
        }
        tasksList = updateList
        _tasks.value = tasksList

    }

    fun deleteTask(task: TaskModel) {
        val index = tasksList.indexOf(task)
        tasksList -= tasksList[index]
        _tasks.value = tasksList
    }
}