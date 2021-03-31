package com.example.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.data.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class MainActivityFragment extends Fragment {
    View view;
    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerview;
    private TaskAdapter adapter;
    private FloatingActionButton addButton;
    private MainViewModel viewModel;
    List<Task> tasks;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main_activity, container, false);

        recyclerview = (RecyclerView)view.findViewById(R.id.tasklist);
        adapter = new TaskAdapter();
        recyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.getAllTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                if (tasks !=null) {
                    adapter.setData(tasks);
                }
            }
        });
        recyclerview.setAdapter(adapter);
        addButton = (FloatingActionButton) view.findViewById(R.id.add_btn);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),AddTaskActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}