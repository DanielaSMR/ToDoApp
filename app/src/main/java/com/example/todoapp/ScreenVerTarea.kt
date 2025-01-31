package com.example.todoapp

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.todoapp.model.ToDo

@Composable
fun ScreenVerTarea(
    tarea: ToDo,
    alActualizar: (String, String, String) -> Unit,
    alCancelar: () -> Unit
)  {
    var titulo by remember { mutableStateOf(tarea.titulo) }
    var descripcion by remember { mutableStateOf(tarea.descripcion) }
    var fechaLimite by remember { mutableStateOf(tarea.dueDate) }
    var errorFecha by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(24.dp)) {
        Text(
            text = "Editar Tarea",
            style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.primary),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp)
        )

        TextField(
            value = titulo,
            onValueChange = { titulo = it },
            label = { Text("Título") },
            modifier = Modifier.fillMaxWidth()
        )

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
            modifier = Modifier.fillMaxWidth(),
            isError = errorFecha
        )

        if (errorFecha) {
            Text("Fecha inválida", color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = alCancelar, colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)) {
                Text("Cancelar")
            }
            Button(onClick = {
                if (fechaLimite.isNotBlank()) {
                    alActualizar(titulo, descripcion, fechaLimite)
                } else {
                    errorFecha = true
                }
            }) {
                Text("Actualizar", color = Color.White)
            }
        }
    }
}
