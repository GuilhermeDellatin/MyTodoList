package com.gfdellatin.mytodolist.data.datasource

import com.gfdellatin.mytodolist.data.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class TaskRepository(private val dao: TaskDao) {

    fun getAll() = dao.getAll()

    fun insert(task: Task) = runBlocking {
        launch(Dispatchers.IO) {
            dao.insert(task)
        }
    }

    fun deleteById(id: Int) = runBlocking {
        launch(Dispatchers.IO) {
            dao.deleteById(id)
        }
    }
}