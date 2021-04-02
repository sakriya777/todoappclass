package com.example.todoapp.data;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@androidx.room.Dao
public interface TodoDao {

    @Query("select * from tasks order by priority")
    public LiveData<List<Task>> getAllTasks();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Task task);

    @Query("delete from tasks")
    void deleteAll();

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);

    @Query("SELECT * FROM tasks WHERE id =:ID ")
    List<Task> getAllTaskbyID(int ID);
}
