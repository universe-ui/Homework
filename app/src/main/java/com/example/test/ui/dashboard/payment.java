package com.example.test.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.test.PassManager;
import com.example.test.R;

public class payment extends AppCompatActivity {
    private TextView tvprice;
    private TextView tvname;
    private TextView[] passwordDots = new TextView[6];
    private Button[] numberButtons = new Button[10];
    private ImageButton btnDelete;

    private StringBuilder passwordBuilder = new StringBuilder();
    private static final int PASSWORD_LENGTH = 6;
    private String account = "default_account";
    private TextView goodname;
    private TextView goodprice;
    int sneakername;
    int price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent intent = getIntent();
        SharedPreferences sp = getSharedPreferences("payment_data", Context.MODE_PRIVATE);
        account = sp.getString("account", "");
        sneakername = (int) intent.getIntExtra("sneakername",0);
        price = (int) intent.getIntExtra("price",0);
        initViews();
        setupClickListeners();
    }

    private void initViews() {
        tvprice = findViewById(R.id.tv_price);
        tvname = findViewById(R.id.tv_name);
        tvname.setText(sneakername);
        tvprice.setText(price);
        // 初始化密码点
        passwordDots[0] = findViewById(R.id.dot_1);
        passwordDots[1] = findViewById(R.id.dot_2);
        passwordDots[2] = findViewById(R.id.dot_3);
        passwordDots[3] = findViewById(R.id.dot_4);
        passwordDots[4] = findViewById(R.id.dot_5);
        passwordDots[5] = findViewById(R.id.dot_6);

        // 初始化数字按钮
        numberButtons[1] = findViewById(R.id.btn_1);
        numberButtons[2] = findViewById(R.id.btn_2);
        numberButtons[3] = findViewById(R.id.btn_3);
        numberButtons[4] = findViewById(R.id.btn_4);
        numberButtons[5] = findViewById(R.id.btn_5);
        numberButtons[6] = findViewById(R.id.btn_6);
        numberButtons[7] = findViewById(R.id.btn_7);
        numberButtons[8] = findViewById(R.id.btn_8);
        numberButtons[9] = findViewById(R.id.btn_9);
        numberButtons[0] = findViewById(R.id.btn_0);

        btnDelete = findViewById(R.id.btn_delete);
    }

    private void setupClickListeners() {
        // 数字按钮点击
        for (int i = 0; i < 10; i++) {
            final int number = i;
            numberButtons[i].setOnClickListener(v -> handleNumberClick(number));
        }

        // 删除按钮
        btnDelete.setOnClickListener(v -> handleDeleteClick());

    }

    private void handleNumberClick(int number) {
        if (passwordBuilder.length() < PASSWORD_LENGTH) {
            passwordBuilder.append(number);
            updatePasswordDots();

            // 密码输入完成
            if (passwordBuilder.length() == PASSWORD_LENGTH) {
                verifyPassword();
            }
        }
    }

    private void handleDeleteClick() {
        if (passwordBuilder.length() > 0) {
            passwordBuilder.deleteCharAt(passwordBuilder.length() - 1);
            updatePasswordDots();
        }
    }

    private void updatePasswordDots() {
        int length = passwordBuilder.length();
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            passwordDots[i].setVisibility(i < length ? View.VISIBLE : View.INVISIBLE);
        }
    }

    private void verifyPassword() {
        // 这里应该调用支付验证接口
        PassManager passManager = new PassManager(this);
        if (passManager.verifyAccount(account,passwordBuilder.toString())) {
            // 支付成功
            Toast.makeText(this,"支付成功！请勿重复支付",Toast.LENGTH_SHORT).show();
            finish();
        } else {
            // 密码错误
            showPasswordError();
        }
    }

    private void showPasswordError() {
        // 震动反馈
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null) {
            vibrator.vibrate(100);
        }

        // 抖动动画
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        findViewById(R.id.password_container).startAnimation(shake);

        // 清空密码
        passwordBuilder.setLength(0);
        updatePasswordDots();

        Toast.makeText(this, "密码错误，请重新输入", Toast.LENGTH_SHORT).show();
    }

}