package com.gfdellatin.mytodolist

import android.app.Application
import com.gfdellatin.mytodolist.data.datasource.AppDatabase
import com.gfdellatin.mytodolist.data.datasource.TaskRepository

class App : Application() {
    val database by lazy { AppDatabase.getDatabase(this) }
    val repository by lazy { TaskRepository(database.taskDao()) }
}