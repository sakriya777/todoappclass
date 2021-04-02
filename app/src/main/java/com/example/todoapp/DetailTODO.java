package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class DetailTODO extends AppCompatActivity {
    TextView text,description, createddate, completiondate, priority;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_t_o_d_o);
        Bundle extras = getIntent().getExtras();

        String title = extras.getString("title");
        String descriptions = extras.getString("description");
        int prioritys = Integer.parseInt(extras.getString("priority"));
        String completiondates = extras.getString("completiondate");
        String createddates = extras.getString("createddate");
        String ids = extras.getString("id");


        text = findViewById(R.id.text);
        description = findViewById(R.id.description);
        createddate = findViewById(R.id.createddate);
        completiondate = findViewById(R.id.completiondate);

        priority = findViewById(R.id.priority);

        text.setText(title);
        description.setText("Details : "+descriptions);
        createddate.setText("Created On : "+createddates);
        if (prioritys == 3){
            priority.setText("Priority : High");
        }
        else if (prioritys == 2){
            priority.setText("Priority : Moderate");
        }
        else if (prioritys == 1){
            priority.setText("Priority : Low");
        }

        completiondate.setText("To be completed on : "+completiondates);
    }
}