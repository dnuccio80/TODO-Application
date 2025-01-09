package com.example.todoapplication.TODOApp.ui.models

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.todoapplication.TODOApp.data.categories.CategoryEntity
import com.example.todoapplication.ui.theme.BusinessColor
import com.example.todoapplication.ui.theme.OtherColor
import com.example.todoapplication.ui.theme.PersonalColor

data class CategoryModel(
    val id:Int = 0,
    val name: String,
    val color: Color,
    val isClicked: Boolean = true
)

object PredetermineCategories{
    val Personal = CategoryModel(name = "Personal", color =  PersonalColor)
    val Business = CategoryModel(name = "Business", color =  BusinessColor)
    val Other = CategoryModel(name = "Other", color =  OtherColor)
}

fun CategoryModel.toCategoryEntity() = CategoryEntity(
    id = id,
    name = name,
    color = color.toArgb(),
    isClicked = isClicked
)



