package com.example.test.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.test.R;

public class PurchaseActivity extends AppCompatActivity {

    private RadioGroup radioGroup;
    private TextView goodname;
    private TextView goodprice;
    private ImageView goodlook;
    private EditText inputaddr;
    private TextView count;
    private ImageButton add;
    private ImageButton sub;
    private Button bt;
    private int num;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.purchase);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        add = findViewById(R.id.add);
        sub = findViewById(R.id.sub);
        num = 1;
        count = findViewById(R.id.count);
        goodlook = findViewById(R.id.goodlook);
        goodprice = findViewById(R.id.goodprice);
        goodname = findViewById(R.id.goodname);
        bt = findViewById(R.id.pay);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int sneakername = (int) bundle.get("sneakername");
        int price = (int) bundle.get("price");
        int lookingurl = (int) bundle.get("lookingurl");
        int unit_price = Integer.parseInt(getString(price).split("¥")[1]);
        bt.setText("立即支付¥"+unit_price);

        goodname.setText(sneakername);
        goodprice.setText(price);
        goodlook.setImageResource(lookingurl);
    }

    public void counts(View view){
        String price = (String) goodprice.getText();
        int unit_price = Integer.parseInt(price.split("¥")[1]);
        int nums = Integer.parseInt((String) count.getText());
        if (view == add){
            nums ++;
            count.setText(Integer.toString(nums));
            bt.setText("立即支付￥"+nums * unit_price);
        }
        if (view == sub){
            if (nums != 1){
                nums--;
                count.setText(Integer.toString(nums));
                bt.setText("立即支付￥"+nums * unit_price);
            }
        }
    }
    public void fuqian(View view){
        radioGroup = findViewById(R.id.radioGroup);
        inputaddr = findViewById(R.id.inputaddr);
        String address = String.valueOf(inputaddr.getText());
        int selectedId = radioGroup.getCheckedRadioButtonId();
        if (selectedId != -1) {
            RadioButton selectedRadioButton = findViewById(selectedId);
            String selectedText = selectedRadioButton.getText().toString();
            Toast.makeText(PurchaseActivity.this,"已完成支付",Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(PurchaseActivity.this,"请选择支付方式！",Toast.LENGTH_SHORT).show();
        }
    }
}