package com.example.dayoungkim.mundan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.dayoungkim.mundan.a_registration.LoginActivity;
import com.example.dayoungkim.mundan.a_registration.SessionManager;

import java.util.Calendar;

public class AdminActivity extends AppCompatActivity  {

    Button admin_logout;
    ImageButton bt_pharase, bt_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);


        //로그아웃 기능
        admin_logout = findViewById(R.id.bt_admin_logout);
        admin_logout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        //phrase 글쓰기로 가기
        bt_pharase = findViewById(R.id.pharase);
        bt_pharase.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminActivity.this, AdminWritingActivity.class);
                startActivity(intent);

            }
        });

    }



}
