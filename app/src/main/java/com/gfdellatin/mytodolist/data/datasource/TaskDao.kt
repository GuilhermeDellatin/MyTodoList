package com.gfdellatin.mytodolist.data.datasource

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gfdellatin.mytodolist.data.model.Task

@Dao
interface TaskDao {

    @Query("SELECT * FROM task_db")
    fun getAll(): LiveData<List<Task>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: Task)

    @Query("DELETE FROM task_db WHERE id = :id")
    suspend fun deleteById(id: Int)
}