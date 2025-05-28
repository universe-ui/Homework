package com.example.test;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class login extends AppCompatActivity {
    public EditText accounti;
    private EditText passwordi;
    Button bsignin;
    ImageButton bsignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        accounti = findViewById(R.id.accountEditText);
        passwordi = findViewById(R.id.passwordEditText);
        bsignin = findViewById(R.id.signInButton);
        bsignup = findViewById(R.id.signup);
        bsignin.setOnClickListener(v -> signin(v));
        bsignup.setOnClickListener(v -> signup(v));
    }

    public void signin(View view){
        String account = accounti.getText().toString();
        PassManager passManager = new PassManager(this);
        if (passManager.isAccountExists(account)){
            String password = passwordi.getText().toString();
            if (passManager.verifyAccount(account,password)){
                Intent main = new Intent(this,MainActivity.class);
                Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
                startActivity(main);
            }
            else {
                Toast.makeText(this,"密码错误！",Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this,"该账号未注册！",Toast.LENGTH_SHORT).show();
        }
    }
    public void signup(View view){
        Intent su = new Intent(login.this,signup.class);
        startActivity(su);
    }
}