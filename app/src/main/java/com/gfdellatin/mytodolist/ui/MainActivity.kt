package com.gfdellatin.mytodolist.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
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

    private val register =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) getAllTasks()
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

            binding.includeEmptyState.emptyState.visibility =
                if (tasks.isNullOrEmpty()) View.VISIBLE
                else {
                    binding.rvTask.visibility = View.VISIBLE
                    View.GONE
                }

            adapter.submitList(tasks)
        })
    }

    private fun insertListeners() {
        binding.fab.setOnClickListener {
            register.launch(Intent(this@MainActivity, AddTaskActivity::class.java))
        }

        adapter.listenerDelete = { task ->
            taskViewModel.deleteById(task)

        }
    }
}