package com.example.assignment1.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.assignment1.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public static final String DB_NAME = "SIMPLE_DATABASE";
    public static final String USER_TABLE = "USERS_TABLE";
    public abstract UserDAO getUserDAO();
}
