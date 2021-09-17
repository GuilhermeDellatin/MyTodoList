package com.gfdellatin.mytodolist.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.gfdellatin.mytodolist.App
import com.gfdellatin.mytodolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val adapter by lazy { TaskListAdapter() }

    private val taskViewModel: TaskViewModel by viewModels {
        TaskViewModelFactory((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvTask.adapter = adapter

        getAllTasks()
        insertListeners()
    }

    private fun getAllTasks() {
        taskViewModel.getAll().observe(this, { tasks ->
            adapter.submitList(tasks)
        })
    }

    private fun insertListeners() {
        binding.fab.setOnClickListener {
            val intent = Intent(this@MainActivity, AddTaskActivity::class.java)
            startActivity(intent)
        }

        adapter.listenerDelete = { task ->
            taskViewModel.deleteById(task)

        }
    }
}