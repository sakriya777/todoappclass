package com.example.todoapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.data.Task;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private List<Task> data;
    public TaskAdapter(List<Task> tasks){
        data = tasks;
    }

    public void  setData(List<Task> tasks){
            data = tasks;
    }
    @NonNull
    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.task_item, parent, false);
        return new ViewHolder(inflater, parent);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if(data == null){
            return 0;
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description;
        public ViewHolder(LayoutInflater inflater, @NonNull ViewGroup parent) {
            super(inflater.inflate(R.layout.task_item, parent, false));
            title = itemView.findViewById(R.id.title_ti);
            description = itemView.findViewById(R.id.description_ti);
        }
    }
}
