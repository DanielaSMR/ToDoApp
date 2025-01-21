package com.example.todoapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ToDoViewModel: ViewModel() {
    private val _hecho = MutableLiveData<Boolean>()
    val hecho: LiveData<Boolean> = _hecho

    private val _tarea = MutableLiveData<String>()
    val tarea: LiveData<String> = _tarea

    fun escribirTarea(tarea: String,hecho: Boolean){
        _tarea.value = tarea
        _hecho.value = false
    }

    fun tareaRealizada(hecho: Boolean){
        _hecho.value = true;
    }
}