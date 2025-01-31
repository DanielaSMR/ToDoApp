package com.example.todoapp

import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todoapp.model.ToDo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen(toDoViewModel: ToDoViewModel, navegarParaAgregar: () -> Unit, mod : Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "To Do List",
                        style = TextStyle(
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.background)
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = navegarParaAgregar) {
                Icon(Icons.Default.Add, contentDescription = "Agregar tarea")
            }
        }
    ) { paddingValues ->
        val listaDeTareas by toDoViewModel.toDoList.observeAsState(emptyList())

        Column(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
            if (listaDeTareas.isEmpty()) {
                Text(
                    text = "Escribe una nueva tarea",
                    style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.primary),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
                )
            }

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(listaDeTareas) { tarea ->
                    ItemToDo(
                        tarea = tarea,
                        alternarCompleto = { toDoViewModel.tareaRealizada(it) },
                        eliminar = { toDoViewModel.eliminarTarea(it) }
                    )
                }
            }
        }
    }
}

@Composable
fun ItemToDo(tarea: ToDo, alternarCompleto: (Int) -> Unit, eliminar: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(
                if (tarea.estaRealizada) MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                else MaterialTheme.colorScheme.surface
            )
            .padding(12.dp)
    ) {
        Checkbox(
            checked = tarea.estaRealizada,
            onCheckedChange = { alternarCompleto(tarea.id) },
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = tarea.titulo,
            style = MaterialTheme.typography.bodyLarge.copy(
                textDecoration = if (tarea.estaRealizada) TextDecoration.LineThrough else TextDecoration.None
            ),
            modifier = Modifier.weight(1f).align(Alignment.CenterVertically)
        )
        IconButton(onClick = { eliminar(tarea.id) }) {
            Icon(Icons.Default.Delete, contentDescription = "Eliminar tarea")
        }
    }
}



