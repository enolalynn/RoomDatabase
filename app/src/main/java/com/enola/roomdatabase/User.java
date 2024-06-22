package com.enola.roomdatabase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {

    @PrimaryKey(autoGenerate = true)
    private Integer id; // to be able to use null value, if int, unable to use null value
    private  String email;
    private String password;

}
