package com.gfdellatin.mytodolist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gfdellatin.mytodolist.data.datasource.TaskRepository
import com.gfdellatin.mytodolist.data.model.Task

class TaskViewModel(private val taskRepository: TaskRepository) : ViewModel() {

    fun getAll(): LiveData<List<Task>> {
        return taskRepository.getAll()
    }

    fun insert(task: Task) {
        taskRepository.insert(task)
    }

    fun deleteById(task: Task) {
        taskRepository.deleteById(task.id)
    }

}

class TaskViewModelFactory(private val repository: TaskRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TaskViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}