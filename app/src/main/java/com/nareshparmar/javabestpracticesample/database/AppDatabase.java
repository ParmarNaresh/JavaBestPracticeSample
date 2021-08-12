package com.nareshparmar.javabestpracticesample.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.nareshparmar.javabestpracticesample.model.User;

@Database(entities = {User.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public static AppDatabase getAppDatabase(Context context)
    {
        AppDatabase db = Room.databaseBuilder(context,
                AppDatabase.class, "my-app-name").build();
        return db;
    }

}
