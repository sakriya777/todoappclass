package com.example.todoapp.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Task.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class
AppDatabase extends RoomDatabase {


    private static AppDatabase INSTANCE;

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public abstract TodoDao todoDao();

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "todo.db")
                            .addCallback(CALLBACK)
//                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback CALLBACK = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);

            TodoDao dao = INSTANCE.todoDao();

            AppDatabase.databaseWriteExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    dao.deleteAll();

                    Task task = new Task("Finish DMA Assignment", "Has to be completed by 1st April", 3, new Date(), "1/4/2021");
                    dao.insert(task);

                    Task task2 = new Task("Finish DS Assignment", "Has to be completed by 5th April", 2, new Date(), "1/4/2021");
                    dao.insert(task2);

                    Task task3 = new Task("Finish PP Assignment", "Has to be completed by 10th April", 2, new Date(), "10/4/2021");
                    dao.insert(task3);

                    Task task4 = new Task("Finish DBA Assignment", "Has to be completed by 15th April", 2, new Date(), "15/4/2021");
                    dao.insert(task4);
                }
            });


        }
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
        }
    };

}
