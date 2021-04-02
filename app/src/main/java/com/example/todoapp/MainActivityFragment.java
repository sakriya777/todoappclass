package com.example.todoapp;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ShareCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.data.AppDatabase;
import com.example.todoapp.data.Repository;
import com.example.todoapp.data.Task;
import com.example.todoapp.data.TodoDao;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class MainActivityFragment extends Fragment{
    View view;
    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerview;
    private TaskAdapter adapter;
    private FloatingActionButton addButton;
    private MainViewModel viewModel;
    private Repository repository;
    AppDatabase db;

    List<Task> tasks;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        repository = new Repository(getActivity().getApplication());
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_main_activity, container, false);
        Toast.makeText(getContext(), "Slide Left to Delete the TODO", Toast.LENGTH_SHORT).show();
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
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerview);
        return view;
    }
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            Task item =  TaskAdapter.getTasks().get(position);
        switch (direction) {
            case ItemTouchHelper.LEFT:
                repository.delete(item);
                adapter.removeItem(position);
                Toast.makeText(getContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                break;

        }
        }
    };
}