package com.example.todoapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.data.Task;
import com.example.todoapp.data.TodoDao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private static final String TAG = TaskAdapter.class.getSimpleName();
    public Context context;
    public static List<Task> data;
    public TaskAdapter(){

    }
    public static List<Task> getTasks() {
        return data;
    }
    public void  setData(List<Task> tasks){
            data = tasks;
            notifyDataSetChanged();
    }
    @NonNull
    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.task_item, parent, false);
        this.context = context;
        return new ViewHolder(inflater, parent);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.ViewHolder holder, int position) {
        Task task = data.get(position);
        holder.bind(task);
    }

    @Override
    public int getItemCount() {
        if(data == null){
            return 0;
        }
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView title, description, priority, createddate, completiondate, daystocomplete;
        public String date1, date;
        public CardView card;
        int dateDifference;

        public ViewHolder(LayoutInflater inflater, @NonNull ViewGroup parent) {
            super(inflater.inflate(R.layout.task_item, parent, false));
            title = itemView.findViewById(R.id.title_ti);
            description = itemView.findViewById(R.id.description_ti);
            priority = itemView.findViewById(R.id.priority_ti);
            completiondate = itemView.findViewById(R.id.completiondate_ti);
            createddate = itemView.findViewById(R.id.createddate_ti);
            daystocomplete = itemView.findViewById(R.id.daystillcompletion_ti);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Calendar c = Calendar.getInstance();
            date1 = sdf.format(c.getTime());
            card = itemView.findViewById(R.id.card);
            itemView.setOnClickListener(this);
        }

        public void bind(Task task){
            title.setText(task.getTitle());
            description.setText(task.getDescription());
            if (task.getPriority() == 3){
                card.setCardBackgroundColor(Color.RED);
            }
            else if (task.getPriority() == 2){
                card.setCardBackgroundColor(Color.YELLOW);
            }
            else if (task.getPriority() == 1){
                card.setCardBackgroundColor(Color.GREEN);
            }
            priority.setText("Priority: "+String.valueOf(task.getPriority()));
            createddate.setText("Created On:"+task.getCreatedDate().toString());
            dateDifference = (int) getDateDiff(new SimpleDateFormat("dd/MM/yyyy"), date1, task.getCompletionDate().toString());
            completiondate.setText("Completion Date: "+task.getCompletionDate().toString());
            daystocomplete.setText("Days left to complete: "+String.valueOf(dateDifference));
        }

        @Override
        public void onClick(View v) {
            Task task = data.get(getAdapterPosition());
            Intent intent = new Intent(v.getContext().getApplicationContext() , DetailTODO.class);
            intent.putExtra("id", task.getId());
            intent.putExtra("title", task.getTitle());
            intent.putExtra("priority", String.valueOf(task.getPriority()));
            intent.putExtra("createddate", task.getCreatedDate().toString());
            intent.putExtra("completiondate", task.getCompletionDate().toString());
            intent.putExtra("description", task.getDescription().toString());
            v.getContext().startActivity(intent);
        }}
    public static long getDateDiff(SimpleDateFormat format, String oldDate, String newDate) {
        try {
            return TimeUnit.DAYS.convert(format.parse(newDate).getTime() - format.parse(oldDate).getTime(), TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    int getId(int position){
        return data.get(position).getId();
    }
    public void removeItem(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }
}
