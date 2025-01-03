package com.example.todoapplication.TODOApp.data.di

import androidx.room.TypeConverter
import com.example.todoapplication.TODOApp.ui.models.CategoryModel
import com.example.todoapplication.TODOApp.ui.models.PredetermineCategories


class Converters {

    @TypeConverter
    fun fromCategoryModel(categoryModel: CategoryModel): String {
        return categoryModel.name
    }

    @TypeConverter
    fun toCategoryModel(name: String): CategoryModel {
        return when(name){
            "Personal" -> PredetermineCategories.Personal
            "Business" -> PredetermineCategories.Business
            "Other" -> PredetermineCategories.Other
            else -> throw IllegalArgumentException("Unknown category")
        }
    }
}