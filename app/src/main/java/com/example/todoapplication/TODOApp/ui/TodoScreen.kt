package com.example.todoapplication.TODOApp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.todoapplication.TODOApp.ui.models.CategoryModel
import com.example.todoapplication.TODOApp.ui.models.TaskModel
import com.example.todoapplication.TODOApp.ui.models.predeterminatedCategories
import com.example.todoapplication.ui.theme.Background
import com.example.todoapplication.ui.theme.BusinessColor
import com.example.todoapplication.ui.theme.CardColor
import com.example.todoapplication.ui.theme.OtherColor
import com.example.todoapplication.ui.theme.PersonalColor

@Composable
fun TodoScreen(innerPadding: PaddingValues) {
    Box(
        Modifier
            .fillMaxSize()
            .background(Background)
            .padding(innerPadding)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Header("Welcome, Damian")
            Spacer(Modifier.height(32.dp))
            SubtitleItem("Categories")
            Spacer(Modifier.height(16.dp))
            CategoriesRecyclerView()
            Spacer(Modifier.height(32.dp))
            SubtitleItem("Tasks")
            Spacer(Modifier.height(16.dp))
            TasksRecyclerView()
        }

    }
}


@Composable
fun Header(text: String) {
    Text(text.uppercase(), style = MaterialTheme.typography.titleLarge)
}

@Composable
fun SubtitleItem(text: String) {
    Text(text.uppercase(), style = MaterialTheme.typography.titleMedium)
}

@Composable
fun CategoriesRecyclerView() {

    val list = listOf(
        CategoryModel("Personal", PersonalColor),
        CategoryModel("Business", BusinessColor),
        CategoryModel("Other", OtherColor),
    )

    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(list) {
            CategoryItem(it)
        }
    }
}

@Composable
fun CategoryItem(category: CategoryModel) {
    Card(
        modifier = Modifier
            .width(130.dp)
            .height(70.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = CardColor,
            contentColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            Text(category.name)
            Spacer(Modifier.size(4.dp))
            HorizontalDivider(Modifier.fillMaxWidth(), color = category.color, thickness = 2.dp)
        }
    }
}

@Composable
fun TasksRecyclerView() {
    val list = listOf(
        TaskModel("Task 1", predeterminatedCategories.Personal),
        TaskModel("Task 2", predeterminatedCategories.Business),
        TaskModel("Task 3", predeterminatedCategories.Other),
    )

    LazyColumn(Modifier.fillMaxWidth().padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(list) {
            TaskItem(it)
        }
    }
}

@Composable
fun TaskItem(task: TaskModel) {

    var checkedValue by rememberSaveable { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth().clickable {
            checkedValue = !checkedValue
        },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = CardColor,
            contentColor = Color.White
        )) {
            Row(Modifier.fillMaxWidth().padding(vertical = 4.dp, horizontal = 8.dp), verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = checkedValue,
                    onCheckedChange = { checkedValue = !checkedValue },
                    colors = CheckboxDefaults.colors(
                        checkedColor = task.category.color,
                        uncheckedColor = task.category.color,
                        checkmarkColor = Color.White
                    )
                )
                Spacer(Modifier.width(8.dp))
                Text(task.description, modifier = Modifier.weight(1f))
                Icon(Icons.Filled.Delete, contentDescription = "delete task", modifier = Modifier.padding(horizontal = 8.dp))

            }
        }
}

