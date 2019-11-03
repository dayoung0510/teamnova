package com.example.dayoungkim.mundan.a_registration;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dayoungkim.mundan.AdminActivity;
import com.example.dayoungkim.mundan.MainActivity;
import com.example.dayoungkim.mundan.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {


    private TextView btn_registerButton;

    private EditText id, password;
    private Button btn_login;
    private static String URL_LOGIN = "http://115.68.222.192/login.php";


    int PRIVATE_MODE = 0;
    private static final String PREF_ID = "LOGIN";
    private static final String LOGIN = "IS_LOGIN";
    public static final String ID = "ID";
    public static final String NICK = "NICK";
    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;



    SessionManager sessionManager;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        sessionManager = new SessionManager(this);



        //위치찾기
        id = findViewById(R.id.nick_text);
        password = findViewById(R.id.password);
        btn_login = findViewById(R.id.btn_login);

        //쉐어드에 저장된 아디비번 얹어주기
        SharedPreferences shared = getSharedPreferences("id_pw", MODE_PRIVATE);
        String id_text = shared.getString("mId", "");
        String pw_text = shared.getString("mPassword", "");
        id.setText(id_text);
        password.setText(pw_text);


        //회원가입으로 넘어가게
        btn_registerButton = findViewById(R.id.registerButton);

        btn_registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mId = id.getText().toString().trim();
                String mPassword = password.getText().toString().trim();

                if(!mId.isEmpty() || !mPassword.isEmpty()) {
                    Login(mId, mPassword);
                }
                else
                    {
                    id.setError("아이디를 입력해주세요.");
                    password.setError("비밀번호를 입력해주세요.");
                }


                //아이디저장용 쉐어드
                SharedPreferences shared = getSharedPreferences("id_pw", MODE_PRIVATE);
                SharedPreferences.Editor editor = shared.edit();
                editor.putString("mId", mId);
                editor.putString("mPassword", mPassword);
                editor.apply();



            }
        });



        //로그아웃 아니면 자동로그인인데, 이거 나중에 손대보자.. (이 코드는 문제있음)
        sharedPreferences = getSharedPreferences(PREF_ID, PRIVATE_MODE);
        editor = sharedPreferences.edit();

        if(editor != null)
        {
//            Login(id_text, pw_text);
        }




    }

    private void Login(final String id, final String password) {
        Log.d("로긴", "1");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");

                            if(success.equals("1")) {
                                for (int i = 0; i < jsonArray.length(); i++)
                                {
                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String id = object.getString("id").trim();
                                    String nick = object.getString("nick").trim();
                                    String no = object.getString("no").trim();

                                    sessionManager.createSession(id, nick, no);

                                    if(id.equals("admin"))
                                    {
                                        Intent adminIntent = new Intent(LoginActivity.this, AdminActivity.class);
                                        startActivity(adminIntent);

                                    }
                                    else
                                    {
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    }




                                }
                            }

                            //아디나 비번 틀렸을 때
                            else {
                                Toast.makeText(LoginActivity.this, "아이디나 비밀번호를 다시 확인해주세요.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        catch (JSONException e)
                        {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {

                Map<String, String> params = new HashMap<>();
                params.put("id", id);
                params.put("password", password);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }


}
