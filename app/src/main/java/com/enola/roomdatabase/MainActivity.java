package com.enola.roomdatabase;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.enola.roomdatabase.databinding.ActivityMainBinding;
import com.enola.roomdatabase.databinding.ItemUserBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MyDatabase myDatabase;
    private UserDao userDao;
    private UserAdapter userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initUi();
        initData();
        //fakeData();
        initListener();

    }

    private void initListener() {
        binding.fabAddUser.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddUser.class);
            startActivityForResult(intent, 123);
        });

        userAdapter.setOnDeleteUserListener(user -> {
            userDao.deleteUser(user);
            refreshData();
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 123 && resultCode == RESULT_OK){
            String email = data.getStringExtra("email");
            String password = data.getStringExtra("password");
            User user = new User(email,password);
            userDao.addUser(user);
            refreshData();
        }

    }

    //    private void fakeData() {
//        userDao.addUser(new User("enola@gmail.com","1234"));
//        refreshData();
//    }
    private void refreshData(){
        List<User> users = userDao.getAllUsers();
        userAdapter.setUsers(users);
    }

    private void initData() {
        userDao = MyDatabase.getInstance(this).getUserDao();
        List<User> users = userDao.getAllUsers();
        userAdapter.setUsers(users);
        refreshData();
    }

    private void initUi() {

        userAdapter = new UserAdapter();
        binding.rvUsers.setAdapter(userAdapter);
        binding.rvUsers.setLayoutManager(new LinearLayoutManager(this));
    }
}