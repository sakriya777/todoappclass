package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.todoapp.data.Repository;
import com.example.todoapp.data.Task;

import java.util.Calendar;
import java.util.Date;

public class AddTaskActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private EditText titleEditText, descriptionEditText;
    private Button addButton, dateselector;
    private Repository repository;
    private int priority;
    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        dateselector = findViewById(R.id.date_selector);
        dateselector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateSelectorDialog();
            }
        });
        titleEditText = findViewById(R.id.titleedittext);
        descriptionEditText = findViewById(R.id.descriptionedittext);
        addButton = findViewById(R.id.submit);

        repository = Repository.getRepository(this.getApplication());


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString();
                String desc = descriptionEditText.getText().toString();
                String complDate = date;
                Task task = new Task(title, desc, priority, new Date(), complDate);
                repository.insert(task);
                finish();
            }
        });
    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.low_priority:
                if (checked)
                    // Low priority
                    priority = 1;
                    break;
            case R.id.moderate_priority:
                if (checked)
                    // moderate priority
                    priority = 2;
                    break;
            case R.id.high_priority:
                if (checked)
                    // high priority
                    priority = 3;
                    break;
        }
    }
    private void showDateSelectorDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,

                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        date = dayOfMonth+"/"+(month+1)+"/"+year;
        dateselector.setText(date);
    }
}