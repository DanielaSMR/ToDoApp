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

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val toDoViewModel: ToDoViewModel = viewModel()
            var nuevoTitulo by remember { mutableStateOf("") }
            Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("ToDo Lista") }
                        )
                    },
                    floatingActionButton = {
                        FloatingActionButton(onClick = {
                            if (nuevoTitulo.isNotEmpty()) {
                                toDoViewModel.escribirTarea(nuevoTitulo)
                                nuevoTitulo = ""
                            }
                        }) {
                            Text("+")
                        }
                    }
                ) { paddingValues ->
                    ToDoScreen(
                        toDoViewModel = toDoViewModel,
                        mod = Modifier.padding(paddingValues),
                        nuevoTitulo = nuevoTitulo,
                        tituloCambia = { nuevoTitulo = it }
                    )
                }
        }
    }
}

