package com.gfdellatin.mytodolist.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gfdellatin.mytodolist.databinding.ActivityAddTaskBinding
import com.gfdellatin.mytodolist.extensions.format
import com.gfdellatin.mytodolist.extensions.text
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*

class AddTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        insertListeners()

        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    private fun insertListeners() {
        binding.tilDate.editText?.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.addOnPositiveButtonClickListener {
                val timeZone = TimeZone.getDefault()
                val offset = timeZone.getOffset(Date().time) * -1
                binding.tilDate.text = Date(it + offset).format()
            }
            datePicker.show(supportFragmentManager, "DATE_PICKER_TAG")
        }

        binding.tilHour.editText?.setOnClickListener {
            val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build()
            timePicker.addOnPositiveButtonClickListener {
                binding.tilHour.text = "${timePicker.hour} ${timePicker.minute}"
            }
            timePicker.show(supportFragmentManager, null)
        }

    }
}