package com.enola.roomdatabase;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.enola.roomdatabase.databinding.ActivityAddUserBinding;

public class AddUser extends AppCompatActivity {
    private ActivityAddUserBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initUi();
        initListener();
    }

    private void initUi() {
    }

    private void initListener() {
        binding.btAddUser.setOnClickListener(v->{
            String email = binding.etEmail.getText().toString();
            String password = binding.etPassword.getText().toString();
            Intent intent = new Intent();
            intent.putExtra("email",email);
            intent.putExtra("password",password);
            setResult(RESULT_OK,intent);
            finish();
        });
    }
}