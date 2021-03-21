package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.todoapp.data.Repository;
import com.example.todoapp.data.Task;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerview;
    private TaskAdapter adapter;
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    repository = Repository.getRepository(this.getApplication());
        recyclerview = findViewById(R.id.tasklist);
        List<Task> tasks = repository.getAllTasks();
        adapter = new TaskAdapter(tasks);
        recyclerview.setAdapter(adapter);

    }
}