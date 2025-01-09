package com.example.todoapplication.TODOApp.ui.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoapplication.TODOApp.domain.categoriesUseCases.AddCategoryUseCase
import com.example.todoapplication.TODOApp.domain.categoriesUseCases.DeleteCategoryUseCase
import com.example.todoapplication.TODOApp.domain.categoriesUseCases.GetAllCategoriesUseCase
import com.example.todoapplication.TODOApp.domain.tasksUseCases.AddTaskUseCase
import com.example.todoapplication.TODOApp.domain.tasksUseCases.DeleteClassUseCase
import com.example.todoapplication.TODOApp.domain.tasksUseCases.GetAllTasksUseCase
import com.example.todoapplication.TODOApp.domain.tasksUseCases.UpdateTaskUseCase
import com.example.todoapplication.TODOApp.ui.models.CategoryModel
import com.example.todoapplication.TODOApp.ui.models.TaskModel
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
    private val addCategoryUseCase: AddCategoryUseCase,
    private val deleteCategoryUseCase: DeleteCategoryUseCase,
    getAllTasksUseCase: GetAllTasksUseCase,
    getAllCategoriesUseCase: GetAllCategoriesUseCase,
    ) : ViewModel() {


    private val _categories = MutableStateFlow<List<CategoryModel>>(emptyList())
    val categories: StateFlow<List<CategoryModel>> = _categories

    init {
        viewModelScope.launch {
            getAllCategoriesUseCase().stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                emptyList()
            ).collect{
                categories -> _categories.value = categories
                Log.i("Damian", "${ _categories.value }")
            }
        }
    }

    fun updateCategory(category: CategoryModel) {
//        val updatedList = categoriesList.map {
//            if(it == category){
//                it.copy(isClicked = !it.isClicked)
//            } else {
//                it
//            }
//        }
//        categoriesList = updatedList
//        _categories.value = categoriesList
//        filterTasks()
    }

    fun addCategory(category: CategoryModel) {
        viewModelScope.launch(Dispatchers.IO) {
            addCategoryUseCase(category)
        }
    }

    fun deleteCategory(category: CategoryModel){
        viewModelScope.launch(Dispatchers.IO) {
            deleteCategoryUseCase(category)
        }
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