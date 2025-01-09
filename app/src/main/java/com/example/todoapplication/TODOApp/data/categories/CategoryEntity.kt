package com.example.todoapplication.TODOApp.data.categories

import androidx.annotation.ColorInt
import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todoapplication.TODOApp.ui.models.CategoryModel

@Entity
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    @ColorInt val color: Int,
    val isClicked: Boolean = true
)

fun CategoryEntity.toCategoryModel() = CategoryModel(
    id = id,
    name = name,
    color = Color(color),
    isClicked = isClicked
)
