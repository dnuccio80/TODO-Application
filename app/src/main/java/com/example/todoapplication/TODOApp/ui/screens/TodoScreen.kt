package com.example.todoapplication.TODOApp.ui.screens

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.todoapplication.TODOApp.ui.models.CategoryModel
import com.example.todoapplication.TODOApp.ui.models.TaskModel
import com.example.todoapplication.TODOApp.ui.viewModels.TaskViewModel
import com.example.todoapplication.ui.theme.Background
import com.example.todoapplication.ui.theme.CardColor
import com.example.todoapplication.ui.theme.CardColor_Selected
import com.example.todoapplication.ui.theme.ContrastColor

@Composable
fun TodoScreen(innerPadding: PaddingValues, viewModel: TaskViewModel) {

    var showAddTaskDialog by rememberSaveable { mutableStateOf(false) }
    var editMode by rememberSaveable { mutableStateOf(false) }
    val taskList by viewModel.categories.collectAsState()


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
            Header("TO-DO List")
            Spacer(Modifier.height(32.dp))
            CategoryHeaderItem(
                onCategoryAdded = { viewModel.addCategory(it) },
                onEditButtonClicked = { editMode = !editMode })
            Spacer(Modifier.height(16.dp))
            CategoriesRecyclerView(
                taskList,
                editMode,
                onItemClicked = { viewModel.updateCategory(it) },
                onDeleteCategory = { viewModel.deleteCategory(it) })
            Spacer(Modifier.height(32.dp))
            SubtitleItem("Tasks")
            Spacer(Modifier.height(16.dp))
            TasksRecyclerView(viewModel)
        }

        FabItem(Modifier.align(Alignment.BottomEnd)) { showAddTaskDialog = true }
        AddTaskDialog(showAddTaskDialog,
            taskList,
            onDismiss = { showAddTaskDialog = false },
            onAddTaskButtonClicked = {
                viewModel.addTask(it)
                showAddTaskDialog = false
            }
        )

    }
}

@Composable
fun CategoryHeaderItem(onCategoryAdded: (CategoryModel) -> Unit, onEditButtonClicked: () -> Unit) {

    var showAddCategoryDialog by rememberSaveable { mutableStateOf(false) }

    Row(
        Modifier
            .fillMaxWidth()
            .padding(end = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SubtitleItem("Categories", Modifier.weight(1f))
        Icon(Icons.Filled.Add,
            contentDescription = "add category",
            tint = Color.White,
            modifier = Modifier.clickable {
                showAddCategoryDialog = true
            })
        Spacer(Modifier.width(32.dp))
        Icon(
            Icons.Filled.Edit,
            contentDescription = "edit categories",
            tint = Color.White,
            modifier = Modifier.clickable {
                onEditButtonClicked()
            })

    }
    AddCategoryDialog(
        show = showAddCategoryDialog,
        onDismiss = { showAddCategoryDialog = false },
        onCategoryAdded = {
            onCategoryAdded(it)
            showAddCategoryDialog = false
        })
}

@Composable
fun FabItem(modifier: Modifier, onClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onClick() },
        modifier = modifier.padding(16.dp),
        containerColor = ContrastColor,
        shape = CircleShape
    ) {
        Icon(Icons.Filled.Add, contentDescription = "add task", tint = Color.White)
    }
}


@Composable
fun Header(text: String) {
    Text(text.uppercase(), style = MaterialTheme.typography.titleLarge)
}

@Composable
fun SubtitleItem(text: String, modifier: Modifier = Modifier) {
    Text(text.uppercase(), style = MaterialTheme.typography.titleMedium, modifier = modifier)
}

@Composable
fun CategoriesRecyclerView(
    taskList: List<CategoryModel>,
    editMode:Boolean,
    onItemClicked: (CategoryModel) -> Unit,
    onDeleteCategory: (CategoryModel) -> Unit
) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(taskList) {
            CategoryItem(
                it,
                editMode,
                onCardClick = { onItemClicked(it) },
                onDeleteCategory = { onDeleteCategory(it) })
        }
    }
}

@Composable
fun CategoryItem(category: CategoryModel,editMode: Boolean, onCardClick: () -> Unit, onDeleteCategory: () -> Unit) {
    Card(
        modifier = Modifier
            .width(130.dp)
            .height(70.dp)
            .clickable {
                onCardClick()
            },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (category.isClicked) CardColor else CardColor_Selected,
            contentColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            Row(Modifier.fillMaxWidth()) {
                Text(category.name, modifier = Modifier.weight(1f))
                if(editMode){
                    Icon(
                        Icons.Filled.Clear,
                        contentDescription = "delete category",
                        modifier = Modifier
                            .size(18.dp)
                            .clickable {
                                onDeleteCategory()
                            })
                }
            }
            Spacer(Modifier.size(4.dp))
            HorizontalDivider(Modifier.fillMaxWidth(), color = category.color, thickness = 2.dp)
        }
    }
}

@Composable
fun TasksRecyclerView(viewModel: TaskViewModel) {

    val list by viewModel.tasks.collectAsState()

    LazyColumn(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(list) {
            TaskItem(
                it,
                onValueChange = { viewModel.updateTask(it) },
                onDeleteIconClick = { viewModel.deleteTask(it) })
        }
    }
}

@Composable
fun TaskItem(task: TaskModel, onValueChange: () -> Unit, onDeleteIconClick: () -> Unit) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onValueChange()
            },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (task.isDone) CardColor_Selected else CardColor,
            contentColor = Color.White
        )
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp, horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = task.isDone,
                onCheckedChange = { onValueChange() },
                colors = CheckboxDefaults.colors(
                    checkedColor = task.category.color,
                    uncheckedColor = task.category.color,
                    checkmarkColor = Color.White
                )
            )
            Spacer(Modifier.width(8.dp))
            Text(
                task.description,
                modifier = Modifier.weight(1f),
                textDecoration = if (task.isDone) TextDecoration.LineThrough else TextDecoration.None
            )
            Icon(
                Icons.Filled.Delete,
                contentDescription = "delete task",
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .clickable {
                        onDeleteIconClick()
                    })
        }
    }
}




