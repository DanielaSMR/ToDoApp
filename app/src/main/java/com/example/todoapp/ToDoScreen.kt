package com.example.todoapp

import android.app.Activity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.example.todoapp.model.ToDo


@Composable
fun ToDoScreen(toDoViewModel: ToDoViewModel,
               mod: Modifier = Modifier,
               nuevoTitulo: String,
               tituloCambia: (String) -> Unit
){
    Box(Modifier
        .fillMaxSize()
        .padding(8.dp))
    {
        Header(Modifier.align(Alignment.TopEnd))
        Body(mod = Modifier.align(Alignment.Center),
            toDoViewModel = toDoViewModel,
            nuevoTitulo = nuevoTitulo,
            tituloCambia = tituloCambia)
        Footer(Modifier.align(Alignment.BottomEnd))
    }
}

@Composable
fun Footer(mod: Modifier) {
}

@Composable
fun Body( mod: Modifier,
          toDoViewModel: ToDoViewModel,
          nuevoTitulo: String,
          tituloCambia: (String) -> Unit) {
    val toDoList by toDoViewModel.toDoList.observeAsState(emptyList())

    Column(modifier = mod.padding(8.dp)) {
        TextField(
            value = nuevoTitulo,
            onValueChange = tituloCambia,
            placeholder = {Text("Nueva tarea")},
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            toDoViewModel.escribirTarea(nuevoTitulo)
            },
            modifier = Modifier.align(Alignment.End)
        ){
            Text("Agregar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(toDoList){ toDo->
                ToDoItem(
                    toDo = toDo,
                    onToggleComplete = {toDoViewModel.tareaRealizada(it)},
                    onDelete = {toDoViewModel.eliminarTarea(it)}
                )
            }
        }
    }
}


@Composable
fun Header(mod: Modifier) {
    val activity = LocalContext.current as? Activity
    Icon(imageVector = Icons.Default.Close,
        contentDescription = "Close APP",
        modifier = mod.clickable { activity?.finish()})
}


@Composable
fun ToDoItem(
    toDo: ToDo,
    onToggleComplete: (Int) -> Unit,
    onDelete: (Int) -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Checkbox(
            checked = toDo.estaRealizada,
            onCheckedChange = { onToggleComplete(toDo.id) }
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = toDo.titulo,
            style = if (toDo.estaRealizada) {
                MaterialTheme.typography.bodyLarge.copy(textDecoration = TextDecoration.LineThrough)
            } else {
                MaterialTheme.typography.bodyLarge
            },
            modifier = Modifier.weight(1f)
        )
        IconButton(onClick = { onDelete(toDo.id) }) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Eliminar")
        }
    }
}