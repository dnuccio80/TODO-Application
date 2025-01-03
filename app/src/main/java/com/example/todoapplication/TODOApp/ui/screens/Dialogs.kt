package com.example.todoapplication.TODOApp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.todoapplication.TODOApp.ui.models.CategoryModel
import com.example.todoapplication.TODOApp.ui.models.TaskModel
import com.example.todoapplication.ui.theme.CardColor_Selected
import com.example.todoapplication.ui.theme.ContrastColor

@Composable
fun AddTaskDialog(
    show: Boolean,
    taskList: List<CategoryModel>,
    onDismiss: () -> Unit,
    onAddTaskButtonClicked: (TaskModel) -> Unit
) {

    var value by rememberSaveable { mutableStateOf("") }
    var categorySelected by rememberSaveable { mutableStateOf(taskList[0].name) }

    val category = taskList.find { it.name == categorySelected }

    if (show) {
        Dialog(
            onDismissRequest = { onDismiss() }
        ) {
            Card(
                Modifier.width(250.dp),
                colors = CardDefaults.cardColors(
                    containerColor = CardColor_Selected,
                    contentColor = Color.White
                ),
                shape = RectangleShape
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    TextField(
                        value = value,
                        onValueChange = { value = it },
                        placeholder = { Text("Add a task..") },
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedPlaceholderColor = Color.White,
                            unfocusedPlaceholderColor = Color.Gray
                        )
                    )
                    HorizontalDivider(
                        Modifier.fillMaxWidth(),
                        thickness = 2.dp,
                        color = Color.White
                    )
                    Spacer(Modifier.size(16.dp))
                    RadioButtonsGroup(categorySelected, taskList) { categorySelected = it }
                    Spacer(Modifier.size(16.dp))
                    Button(
                        onClick = {
                            if(value.isNotEmpty()){
                                onAddTaskButtonClicked(TaskModel( description = value, category =  category!!))
                                value = ""
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ContrastColor,
                            contentColor = Color.White
                        )
                    ) {
                        Text(("Add Task"))
                    }
                }
            }

        }
    }
}


@Composable
fun RadioButtonsGroup(categorySelected: String, taskList: List<CategoryModel>, onButtonClicked:(String) -> Unit) {

    taskList.forEach {
        Row(Modifier.fillMaxWidth().clickable {
            onButtonClicked(it.name)
        }, verticalAlignment = Alignment.CenterVertically) {
            RadioButton(
                selected = categorySelected == it.name,
                onClick = { onButtonClicked(it.name) },
                colors = RadioButtonDefaults.colors(
                    selectedColor = it.color,
                    unselectedColor = it.color
                )
            )
            Spacer(Modifier.width(4.dp))
            Text(it.name)

        }
    }

}