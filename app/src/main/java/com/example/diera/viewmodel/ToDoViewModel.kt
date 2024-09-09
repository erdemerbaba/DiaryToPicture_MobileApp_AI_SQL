package com.example.diera.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.diera.data.ToDoItem

class ToDoViewModel : ViewModel() {
    private val _items = MutableLiveData<List<ToDoItem>>()
    val items: LiveData<List<ToDoItem>> get() = _items

    init {
        _items.value = mutableListOf()
    }

    fun addItem(task: String) {
        val newItem = ToDoItem(id = System.currentTimeMillis(), task = task, isDone = false)
        _items.value = _items.value?.plus(newItem)
    }

    fun toggleItemDone(id: Long) {
        _items.value = _items.value?.map {
            if (it.id == id) it.copy(isDone = !it.isDone) else it
        }
    }
}
