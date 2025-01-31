package com.example.todoapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoapp.model.ToDo

class ToDoViewModel: ViewModel() {
    private val _toDoList = MutableLiveData<List<ToDo>>(emptyList())
    val toDoList: LiveData<List<ToDo>> get() = _toDoList

    private var numId = 1

    fun escribirTarea(titulo: String, descripcion: String, dueDate: String) {
        if (titulo.isNotEmpty()) {
            val nuevoToDo = ToDo(
                id = numId++,
                titulo = titulo,
                descripcion = descripcion,
                dueDate = dueDate,
                estaRealizada = false
            )
            _toDoList.value = _toDoList.value?.plus(nuevoToDo)
        }
    }

    fun actualizarTarea(id: Int, nuevoTitulo: String, nuevaDescripcion: String, nuevaFecha: String) {
        _toDoList.value = _toDoList.value?.map { tarea ->
            if (tarea.id == id) {
                tarea.copy(
                    titulo = nuevoTitulo,
                    descripcion = nuevaDescripcion,
                    dueDate = nuevaFecha
                )
            } else {
                tarea
            }
        }
    }

    fun tareaRealizada(id: Int){
        _toDoList.value = _toDoList.value?.map { toDo ->
            if(toDo.id == id){
                toDo.copy(estaRealizada = !toDo.estaRealizada)
            }else{
                toDo
            }
        }
    }

    fun eliminarTarea(id: Int){
        _toDoList.value = _toDoList.value?.filter { it.id != id }
    }
}