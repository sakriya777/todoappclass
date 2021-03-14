package com.example.todoapp;

import androidx.room.Delete;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@androidx.room.Dao
interface TodoDao {

    @Query("select * from tasks order by priority")
    public List<Task> getAllTasks();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Task task);

    @Query("delete from tasks")
    void deleteAll();

    @Delete
    void delete(Task task);

    @Update
    void update(Task task);
}
