package com.example.user.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.user.R;

public class HomeActivity extends AppCompatActivity {

    private Button btnHomeReg;
    private Button btnHomeLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        initEvent();
    }

    private void initEvent() {
        btnHomeReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,RegiActivity.class));
            }
        });
    }

    private void initView() {
        btnHomeReg = findViewById(R.id.btn_home_reg);
        btnHomeLogin = findViewById(R.id.btn_home_login);
    }
}
