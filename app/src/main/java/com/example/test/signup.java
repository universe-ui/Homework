package com.example.test;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.regex.Pattern;

public class signup extends AppCompatActivity {
    private static final Pattern NUMBER_PATTERN = Pattern.compile("^\\d+$");
    private EditText accountu;
    private EditText passwordu;
    private Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        accountu = findViewById(R.id.accountEditText2);
        passwordu = findViewById(R.id.passwordEditText2);
        signup = findViewById(R.id.signUpButton);
        signup.setOnClickListener(v -> test(v));

        Intent intent = getIntent();
    }
    public void test(View view){
        String input = accountu.getText().toString();
        String signpas = passwordu.getText().toString();
        PassManager passManager = new PassManager(this);
        if (isValidNumber(input) && isSixDigits(signpas)) {
            if (passManager.isAccountExists(input)){
                Toast.makeText(this,"该账号已存在！",Toast.LENGTH_SHORT).show();
            }
            else{
                int signacc = Integer.parseInt(input);
                PassItem item = new PassItem(input,signpas);
                passManager.add(item);
                Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();
                finish();
            }
        } else {
            Toast.makeText(this,"账号仅由数字构成且密码为6位数字",Toast.LENGTH_SHORT).show();
        }
    }
    private boolean isValidNumber(String input) {
        return !TextUtils.isEmpty(input) && NUMBER_PATTERN.matcher(input).matches();
    }
    public static boolean isSixDigits(String signpas) {
        // 要保证 signpas 不为 null，防止出现 NullPointerException
        return signpas != null && signpas.matches("^\\d{6}$");
    }
}