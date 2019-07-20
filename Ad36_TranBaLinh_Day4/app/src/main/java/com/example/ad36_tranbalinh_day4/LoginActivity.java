package com.example.ad36_tranbalinh_day4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    ArrayList<Food> foodList;
    ImageButton btnLogin;
    EditText etLoginName;
    EditText etPassWord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = findViewById(R.id.btnLogin);
        etLoginName = findViewById(R.id.etLoginName);
        etPassWord = findViewById(R.id.etPassWord);
        foodList = new ArrayList<>();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((etLoginName.getText() != null || !etLoginName.getText().equals(""))&& (etPassWord.getText()!=null || !etPassWord.getText().equals("")) ){
                    Intent intent = new Intent(getBaseContext(),Content.class);
                    intent.putExtra("loginName",etLoginName.getText().toString());
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getBaseContext(),"Tên đăng nhập hoặc mật khẩu trống !",Toast.LENGTH_LONG).show();
                }


            }
        });
    }
}
