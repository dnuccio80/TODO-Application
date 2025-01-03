package com.example.todoapplication.TODOApp.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapplication.TODOApp.domain.AddTaskUseCase
import com.example.todoapplication.TODOApp.domain.DeleteClassUseCase
import com.example.todoapplication.TODOApp.domain.GetAllTasksUseCase
import com.example.todoapplication.TODOApp.domain.UpdateTaskUseCase
import com.example.todoapplication.TODOApp.ui.models.CategoryModel
import com.example.todoapplication.TODOApp.ui.models.TaskModel
import com.example.todoapplication.TODOApp.ui.models.PredetermineCategories
import com.example.todoapplication.ui.theme.BusinessColor
import com.example.todoapplication.ui.theme.OtherColor
import com.example.todoapplication.ui.theme.PersonalColor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteClassUseCase,
    getAllTasksUseCase: GetAllTasksUseCase,
    ) : ViewModel() {

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

    fun addCategory(category: CategoryModel) {
        categoriesList += category
        _categories.value = categoriesList
    }

    fun deleteCategory(category: CategoryModel){
        val index = categoriesList.indexOf(category)
        categoriesList -= categoriesList[index]
        _categories.value = categoriesList
    }

    private fun filterTasks() {
//        val filteredCategoryList = categoriesList.filter { it.isClicked }
//        val filteredTaskList = tasksList.filter {
//            filteredCategoryList.contains(it.category)
//        }
//        _tasks.value = filteredTaskList

    }

    // TASKS

    private val taskList = getAllTasksUseCase().stateIn(
        viewModelScope, SharingStarted.Lazily, emptyList()
    )
    val tasks: StateFlow<List<TaskModel>> = taskList

    fun addTask(task:TaskModel){
        viewModelScope.launch(Dispatchers.IO) {
            addTaskUseCase(task)
        }
    }

    fun updateTask(task:TaskModel){
        val updatedTask = task.copy(isDone = !task.isDone)
        viewModelScope.launch(Dispatchers.IO) {
            updateTaskUseCase(updatedTask)
        }
    }

    fun deleteTask(task: TaskModel) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteTaskUseCase(task)
        }
    }
}