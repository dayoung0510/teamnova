package com.example.dayoungkim.mundan.b_writingText;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dayoungkim.mundan.HomeFragment;
import com.example.dayoungkim.mundan.MainActivity;
import com.example.dayoungkim.mundan.R;
import com.example.dayoungkim.mundan.a_registration.SessionManager;
import com.example.dayoungkim.mundan.d_viewText.MainAdapter;
//import com.example.dayoungkim.mundan.d_viewText.MainPresenter;

import java.util.HashMap;

public class WritingTextActivity extends AppCompatActivity implements WritingTextView {


    EditText editText;
    RadioGroup radioGroup;
    ProgressDialog progressDialog;

    Button confirm, edit, delete;
    TextView cancel;

    ApiInterface apiInterface;
    WritingTextPresenter presenter;

    //수정할때 넣은 것
    int no;
    String note, user_cat;
    RadioButton rb_essay, rb_novel, rb_poem;


    SessionManager sessionManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing_text);

        //세션매니저 받아오기
        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> user = sessionManager.getUserDetail();
        String mId = user.get(sessionManager.ID);
        String mNick = user.get(sessionManager.NICK);


        editText = (EditText) findViewById(R.id.editText);
        confirm = (Button) findViewById(R.id.bt_confirm);
        cancel = (TextView) findViewById(R.id.tv_cancel);
        edit = (Button) findViewById(R.id.bt_edit);
        delete = (Button) findViewById(R.id.bt_delete);


        //라디오그룹 위치 찾기
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        rb_essay = (RadioButton) findViewById(R.id.radioButton_essay);
        rb_novel = (RadioButton) findViewById(R.id.radioButton_novel);
        rb_poem = (RadioButton) findViewById(R.id.radioButton_poem);



        //라디오그룹에 체크드체인지리스너 달아줌 (셋온클릭리스너가 아님!!)
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                //스트링 rb는 체크드id의 인트값이고 이걸 스트링값으로 바꿔서 받아옴
                String rb = ((RadioButton) findViewById(checkedId)).getText().toString();

                SharedPreferences sp_category = getSharedPreferences("category", MODE_PRIVATE );
                SharedPreferences.Editor ed = sp_category.edit();
                ed.putString("category", rb);
                ed.apply();

            }
        });



        //기다리는동안 프로그레스다이얼로그 뜨게 일단은 선언만
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("잠시만 기다려주세요.");


        presenter = new WritingTextPresenter(this);

        //수정기능 구현할 때 넣은 것
        Intent intent = getIntent();
        no = intent.getIntExtra("no", 0);
        note = intent.getStringExtra("note");
        user_cat = intent.getStringExtra("user_cat");

        //수정 모드일 때
        if(no != 0)
        {
            //저장된 내용 불러오기
            editText.setText(note);
            //만약 수정모드라면 확인버튼 숨기기
            confirm.setVisibility(View.GONE);
            edit.setVisibility(View.VISIBLE);


            if(user_cat.equals("에세이"))
            {
                rb_essay.setChecked(true);
            }
            else if(user_cat.equals("소설"))
            {
                rb_novel.setChecked(true);
            }
            else
            {
                rb_poem.setChecked(true);
            }

        }

        //수정모드 아니고 새글쓰기 모드일 때
        else
        {
            confirm.setVisibility(View.VISIBLE);
            edit.setVisibility(View.GONE);
            delete.setVisibility(View.GONE);
        }



        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SharedPreferences sp_category = getSharedPreferences("category", MODE_PRIVATE);
                String user_cat = sp_category.getString("category", "");


                String nick = mNick;
                String id = mId;
                String note = editText.getText().toString().trim();
                String category = user_cat;


                if(note.isEmpty())
                {
                    editText.setError("내용을 입력해주세요");
                }
                else
                {
                    presenter.saveNote(id, nick, note, category);
                }


            }
        });

        //취소버튼
        cancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //수정버튼
        edit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                SharedPreferences sp_category = getSharedPreferences("category", MODE_PRIVATE);
                String user_cat = sp_category.getString("category", "");

                String note = editText.getText().toString().trim();
                String category = user_cat;
                presenter.updateNote(no, note, category);

            }

        });

        //삭제버튼
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(WritingTextActivity.this);

                builder.setTitle("삭제");
                builder.setMessage("정말 삭제하시겠습니까?");
                builder.setIcon(R.drawable.delete);

                builder.setNegativeButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        presenter.deleteNote(no);
                        Toast.makeText(WritingTextActivity.this, "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });

                builder.setPositiveButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                         dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });



    }






    @Override
    public void showProgress() {
//        progressDialog.show();

    }

    @Override
    public void hideProgress() {
//        progressDialog.hide();

    }

    @Override
    public void onRequestSuccess(String message) {

//        Toast.makeText(WritingTextActivity.this, "성공", Toast.LENGTH_SHORT).show();
        // 셋리절트오케이 한줄때문에새로고침 안됐다 씨부랄꺼
        setResult(RESULT_OK);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

//        finish(); //메인액티비티로 돌아가기

    }

    @Override
    public void onRequestError(String message) {

        Toast.makeText(WritingTextActivity.this, "실패", Toast.LENGTH_SHORT).show();
        finish(); //메인액티비티로 돌아가기

    }




}
