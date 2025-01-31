package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.model.ToDo

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val toDoViewModel: ToDoViewModel = viewModel()
            var pantallaActual by remember { mutableStateOf("lista") }
            var tareaSeleccionada by remember { mutableStateOf<ToDo?>(null) }

            Scaffold(
                topBar = {
                    TopAppBar(title = { Text("Lista de Tareas") })
                }
            ) { paddingValues ->
                when (pantallaActual) {
                    "lista" -> Screen(
                        toDoViewModel = toDoViewModel,
                        navegarParaAgregar = { pantallaActual = "agregar" },
                        mod = Modifier.padding(paddingValues)
                    )
                    "agregar" -> ScreenAgregarTarea(
                        alAgregar = { titulo, descripcion, fechaLimite ->
                            toDoViewModel.escribirTarea(titulo, descripcion, fechaLimite)
                            pantallaActual = "lista"
                        },
                        alCancelar = { pantallaActual = "lista" },
                        mod = Modifier.padding(paddingValues)
                    )
                    "verTarea" -> tareaSeleccionada?.let { tarea ->
                        ScreenVerTarea(
                            tarea = tarea,
                            alActualizar = { titulo, descripcion, fechaLimite ->
                                toDoViewModel.actualizarTarea(tarea.id, titulo, descripcion, fechaLimite)
                                pantallaActual = "lista"
                            },
                            alCancelar = { pantallaActual = "lista" }
                        )
                    } ?: Text("Tarea no encontrada")
                }
            }
        }
    }
}

