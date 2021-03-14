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

    @Delete
    void deleteAll();

    @Query("delete from tasks where id = :id")
    void delete(int id);

    @Update
    void update(Task task)
}
