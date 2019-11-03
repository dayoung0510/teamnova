package com.example.dayoungkim.mundan;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dayoungkim.mundan.a_registration.SessionManager;
import com.example.dayoungkim.mundan.d_viewText.PharasesFragment;
import com.example.dayoungkim.mundan.f_search.SearchFragment;
import com.example.dayoungkim.mundan.g_mypage.MypageFragment;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {





    SessionManager sessionManager;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//
//
//
//
//
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//
//
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//
//        //네비게이션드로어 아이콘에 회색 없애기
//        navigationView.setItemIconTintList(null);
//
//
//
//        //네비 헤더에 글자 바꾸기 위해서 넣은 것
//        View nav_header_view = navigationView.getHeaderView(0);
//
//
//
//
//
//        //세션매니저 받아옴
//        sessionManager = new SessionManager(this);
//        sessionManager.checkLogin();
//
//        HashMap<String, String> user = sessionManager.getUserDetail();
//        String mId = user.get(sessionManager.ID);
//        String mNick = user.get(sessionManager.NICK);
//
//
//
//        //네비헤더에 아이디, 닉네임값 배치
//        TextView nav_header_id_text = (TextView) nav_header_view.findViewById(R.id.id_text);
//        TextView nav_header_nick_text = (TextView) nav_header_view.findViewById(R.id.nick_text);
//
//        nav_header_id_text.setText(mId);
//        nav_header_nick_text.setText(mNick);
//
//
//
//
//
//
//        //프래그먼트 첫 화면 설정
//        Fragment fragment = new HomeFragment();
//        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//        ft.replace(R.id.content_fragment_layout, fragment);
//        ft.commit();
//        //맨 처음에 mainactivity 툴바에 뜨는거 없애기
//        getSupportActionBar().setTitle("");







    }


    @Override
    protected void onResume() {
        super.onResume();



        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);





        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);


        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //네비게이션드로어 아이콘에 회색 없애기
        navigationView.setItemIconTintList(null);



        //네비 헤더에 글자 바꾸기 위해서 넣은 것
        View nav_header_view = navigationView.getHeaderView(0);





        //세션매니저 받아옴
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();

        HashMap<String, String> user = sessionManager.getUserDetail();
        String mId = user.get(sessionManager.ID);
        String mNick = user.get(sessionManager.NICK);



        //네비헤더에 아이디, 닉네임값 배치
        TextView nav_header_id_text = (TextView) nav_header_view.findViewById(R.id.id_text);
        TextView nav_header_nick_text = (TextView) nav_header_view.findViewById(R.id.nick_text);

        nav_header_id_text.setText(mId);
        nav_header_nick_text.setText(mNick);






        //프래그먼트 첫 화면 설정
        Fragment fragment = new HomeFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_fragment_layout, fragment);
        ft.commit();
        //맨 처음에 mainactivity 툴바에 뜨는거 없애기
        getSupportActionBar().setTitle("");




    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            // 뒤로가기하면 자꾸 로그인화면으로 가져가지고 주석처리 해버렸다
            // super.onBackPressed();
        }
    }






    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        Fragment fragment = null;
        String title = "";




        //여기있는 R.id 홈, 마이페이지...는 레이아웃-메뉴-activity_main_drawer.xml 에 있는 것들임
        if (id == R.id.home)
        {
            fragment = new HomeFragment();
            title = "";

        }

        else if (id == R.id.mypage)
        {
            fragment = new MypageFragment();
            title = "";
        }

        else if (id == R.id.pharase)
        {
            fragment = new PharasesFragment();
            title = "";
        }

        else if (id == R.id.image)
        {

            title = "";

        }

        else if (id == R.id.message)
        {

        }

        else if (id == R.id.search)
        {

            fragment = new SearchFragment();
            title = "";

        }





        if (fragment != null)
        {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_fragment_layout, fragment);
            ft.commit();
        }




        // set the toolbar title
        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setTitle(title);
        }




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }









}