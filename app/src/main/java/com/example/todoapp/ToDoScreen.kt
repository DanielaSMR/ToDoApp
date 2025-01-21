package com.example.todoapp

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ToDoScreen(toDoViewModel: ToDoViewModel){
    Box(Modifier
        .fillMaxSize()
        .padding(8.dp))
    {
        Header(Modifier.align(Alignment.TopEnd))
        Body(Modifier.align(Alignment.Center), toDoViewModel)
        Footer()
    }
}

@Composable
fun Footer() {
}

@Composable
fun Body(mod: Modifier,toDoViewModel: ToDoViewModel) {
    val hecho by toDoViewModel.hecho.observeAsState("")
    val tarea by toDoViewModel.tarea.observeAsState("")
    val escribirTarea by toDoViewModel.escribirTarea.observeAsState("",false)

}

fun ToDoList(){

}

fun getList(): List<ToDo>{

}

@Composable
fun Header(mod: Modifier) {
    val activity = LocalContext.current as? Activity
    Icon(imageVector = Icons.Default.Close,
        contentDescription = "Close APP",
        modifier = mod.clickable { activity?.finish()})
}
