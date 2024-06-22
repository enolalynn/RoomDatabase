package com.enola.roomdatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    private static MyDatabase myDatabase;
    public abstract UserDao getUserDao();

    private MyDatabase(Context context){

    }

    public static MyDatabase getInstance(Context context){
        if(myDatabase == null){
            myDatabase = Room.databaseBuilder(context, MyDatabase.class, "my_database").build();
        }
        return myDatabase;
    }

}
