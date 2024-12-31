package com.example.todoapplication.TODOApp.ui.models

import androidx.compose.ui.graphics.Color
import com.example.todoapplication.ui.theme.BusinessColor
import com.example.todoapplication.ui.theme.OtherColor
import com.example.todoapplication.ui.theme.PersonalColor

data class CategoryModel(
    val name: String,
    val color: Color,
    val isClicked: Boolean = true
)

object predeterminatedCategories{
    val Personal = CategoryModel("Personal", PersonalColor)
    val Business = CategoryModel("Business", BusinessColor)
    val Other = CategoryModel("Other", OtherColor)
}

