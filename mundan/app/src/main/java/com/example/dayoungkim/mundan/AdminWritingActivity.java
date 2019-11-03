package com.example.dayoungkim.mundan;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AdminWritingActivity extends AppCompatActivity{


    TextView choiceDay, choiceTime, tv_cancel;
    Calendar c;
    Button bt_datepick, bt_timepick, bt_confirm;





    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_writing);



        //캘린더 객체 생성
        c = Calendar.getInstance();
//        int now_hour = c.get(c.HOUR_OF_DAY);
//        int now_minute = c.get(c.MINUTE);
        int now_hour = c.get(Calendar.HOUR_OF_DAY);
        int now_minute = c.get(Calendar.MINUTE);
        int now_year = c.get(Calendar.YEAR);
        int now_month = c.get(Calendar.MONTH);
        int now_day = c.get(Calendar.DAY_OF_MONTH);

        choiceDay = findViewById(R.id.choice_day);
        choiceTime = findViewById(R.id.choice_time);


        //날짜설정 눌렀을 때
        bt_datepick = findViewById(R.id.pick_day);
        bt_datepick.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                DatePickerDialog.OnDateSetListener mDateSetListener =
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int selectYear, int selectMonth, int selectDay)
                            {
                                String selectedDate = String.valueOf(selectYear) +"년 " + String.valueOf(selectMonth +1) +"월 " + String.valueOf(selectDay) +"일";
                                choiceDay.setText(selectedDate);
                            }
                        };

                DatePickerDialog dateAlert = new DatePickerDialog(AdminWritingActivity.this, mDateSetListener, now_year, now_month, now_day);
                dateAlert.show();
            }
        });

        bt_timepick = findViewById(R.id.pick_time);
        bt_timepick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog.OnTimeSetListener mTimeSetListener =
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int selectHour, int selectMinute)
                            {
                                String selectedTime = String.valueOf(selectHour) +"시 " + String.valueOf(selectMinute) +"분";
                                choiceTime.setText(selectedTime);
                            }
                        };

                TimePickerDialog timeAlert = new TimePickerDialog(AdminWritingActivity.this, mTimeSetListener, now_hour, now_minute, false);
                timeAlert.show();
            }
        });




        bt_confirm = findViewById(R.id.confirm);
        bt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //다 작성하고 확인 버튼 눌렀을 때
            }
        });


        tv_cancel = findViewById(R.id.cancel);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });




    }











}
