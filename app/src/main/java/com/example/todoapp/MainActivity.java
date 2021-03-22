package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.todoapp.data.Repository;
import com.example.todoapp.data.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView recyclerview;
    private TaskAdapter adapter;

//    Repository repository;

    private FloatingActionButton addButton;
    private MainViewModel viewModel;
    List<Task> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerview = findViewById(R.id.tasklist);

//        repository = Repository.getRepository(this.getApplication());
//        tasks = repository.getAllTasks();
        adapter = new TaskAdapter();
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
         viewModel.getAllTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                if (tasks !=null) {
                    adapter.setData(tasks);
                }
            }
        });

//
        recyclerview.setAdapter(adapter);

        addButton = findViewById(R.id.add_btn);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(intent);
            }
        });
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        tasks = viewModel.getAllTasks();
//        adapter.setData(tasks);
//    }
}