package com.example.todoapp.model

data class ToDo(
    val id: Int,
    val titulo: String,
    val descripcion: String,
    val dueDate: String,
    val estaRealizada: Boolean = false
)
