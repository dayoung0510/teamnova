package com.example.dayoungkim.mundan.a_registration;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.toolbox.StringRequest;
import com.example.dayoungkim.mundan.MainActivity;
import com.example.dayoungkim.mundan.a_registration.LoginActivity;

import java.util.HashMap;

public class SessionManager {

    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0;

    private static final String PREF_ID = "LOGIN";
    private static final String LOGIN = "IS_LOGIN";
    public static final String ID = "ID";
    public static final String NICK = "NICK";
    public static final String NO = "NO";


    public SessionManager(Context context) {

        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_ID, PRIVATE_MODE);
        editor = sharedPreferences.edit();


    }


    public void createSession(String id, String nick, String no) {

        editor.putBoolean("LOGIN", true);
        editor.putString("ID", id);
        editor.putString("NICK", nick);
        editor.putString("NO", no);
        editor.apply();

    }


    //이거 이상하다.. 원래 ture 아니고 예제에서는 falsed였는데, 자꾸 !=checkLogin의 결과가 나와가지고 true로 바꾸니까 됨; 찝찝쓰
    //그래서 이거를 false로 바꾸고 밑에 if(!this...)였는데 느낌표 뺐음 (예제에는 느낌표 있었음..)
    public boolean isLogin() {

        return sharedPreferences.getBoolean(LOGIN, false);

    }


    public void checkLogin() {
        if (this.isLogin()) {
            Intent i = new Intent (context, LoginActivity.class);
            context.startActivity(i);
            ((MainActivity) context).finish();
        }

    }

    public HashMap<String, String> getUserDetail() {
        HashMap <String, String> user = new HashMap<>();
        user.put(ID, sharedPreferences.getString(ID, null));
        user.put(NICK, sharedPreferences.getString(NICK, null));
        user.put(NO, sharedPreferences.getString(NO, null));

        return user;
    }

    public void logout() {
        editor.clear();
        Log.d("로그인", "로그아웃1");
        editor.commit();
        Log.d("로그인", "로그아웃2");
        Intent i = new Intent(context, LoginActivity.class);
        context.startActivity(i);
        ((MainActivity) context).finish();
    }


}
