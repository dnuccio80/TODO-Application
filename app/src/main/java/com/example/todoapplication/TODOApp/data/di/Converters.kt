package com.example.todoapplication.TODOApp.data.di

import androidx.annotation.ColorInt
import androidx.compose.ui.graphics.Color
import androidx.room.TypeConverter
import com.example.todoapplication.TODOApp.data.categories.CategoryEntity
import com.example.todoapplication.TODOApp.data.categories.toCategoryModel
import com.example.todoapplication.TODOApp.ui.models.CategoryModel
import com.example.todoapplication.TODOApp.ui.models.PredetermineCategories
import com.example.todoapplication.TODOApp.ui.models.toCategoryEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromCategoryModel(categoryModel: CategoryModel): String {
        return gson.toJson(categoryModel)
    }

    @TypeConverter
    fun toCategoryModel(name:String): CategoryModel {
        val type = object : TypeToken<CategoryModel>() {}.type
        return gson.fromJson(name, type)
    }

}