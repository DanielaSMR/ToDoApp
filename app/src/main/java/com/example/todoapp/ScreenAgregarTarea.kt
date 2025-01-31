package com.example.todoapp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ScreenAgregarTarea(
    alAgregar: (String, String, String) -> Unit,
    alCancelar: () -> Unit,
    mod: Modifier = Modifier
){
    var titulo by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var fechaLimite by remember { mutableStateOf("") }
    var errorTitulo by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(24.dp)) {
        Text(
            text = "Escribe una nueva tarea",
            style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.primary),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp)
        )

        TextField(
            value = titulo,
            onValueChange = { titulo = it; errorTitulo = it.isBlank() },
            label = { Text("Título") },
            isError = errorTitulo,
            modifier = Modifier.fillMaxWidth()
        )
        if (errorTitulo) {
            Text("Campo vacío", color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(12.dp))

        TextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        TextField(
            value = fechaLimite,
            onValueChange = { fechaLimite = it },
            label = { Text("Fecha límite") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = alCancelar, colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)) {
                Text("Cancelar")
            }
            Button(onClick = {
                if (titulo.isNotBlank()) {
                    alAgregar(titulo, descripcion, fechaLimite)
                } else {
                    errorTitulo = true
                }
            }) {
                Text("Agregar", color = Color.White)
            }
        }
    }
}

