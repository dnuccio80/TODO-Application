package com.example.todoapplication.TODOApp.data.categories

import android.util.Log
import com.example.todoapplication.TODOApp.ui.models.CategoryModel
import com.example.todoapplication.TODOApp.ui.models.toCategoryEntity
import com.example.todoapplication.ui.theme.BusinessColor
import com.example.todoapplication.ui.theme.OtherColor
import com.example.todoapplication.ui.theme.PersonalColor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryRepository @Inject constructor(private val categoryDao: CategoryDao) {


    suspend fun getAllCategories(): Flow<List<CategoryModel>> {
        val categoriesInDb = categoryDao.getAllCategories().firstOrNull()
        if (categoriesInDb.isNullOrEmpty()) {
            defaultCategories.forEach { defaultCategory ->
                categoryDao.addCategory(defaultCategory.toCategoryEntity())
            }
        }

        return categoryDao.getAllCategories()
            .map { item -> item.map { it.toCategoryModel() } }
    }


    suspend fun addCategory(category:CategoryModel){
        categoryDao.addCategory(category.toCategoryEntity())
    }

    suspend fun deleteCategory(category: CategoryModel){
        categoryDao.deleteCategory(category.toCategoryEntity())
    }

    private val defaultCategories = listOf(
        CategoryModel(name = "Personal", color =  PersonalColor),
        CategoryModel(name = "Business", color =  BusinessColor),
        CategoryModel(name = "Other", color =  OtherColor),
    )

}