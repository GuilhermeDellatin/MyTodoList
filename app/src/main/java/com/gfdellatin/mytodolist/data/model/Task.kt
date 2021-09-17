package com.gfdellatin.mytodolist.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_db")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val hour: String,
    val date: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Task

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }
}
