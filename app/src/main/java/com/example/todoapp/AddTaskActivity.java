package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.todoapp.data.Repository;
import com.example.todoapp.data.Task;

import java.util.Date;

public class AddTaskActivity extends AppCompatActivity {

    private EditText titleEditText, descriptionEditText;
    private Button addButton;
    private Repository repository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        titleEditText = findViewById(R.id.titleedittext);
        descriptionEditText = findViewById(R.id.descriptionedittext);
        addButton = findViewById(R.id.submit);

        repository = Repository.getRepository(this.getApplication());

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString();
                String desc = descriptionEditText.getText().toString();
                Task task = new Task(title, desc, 1, new Date());
                repository.insert(task);
                finish();
            }
        });
    }
}