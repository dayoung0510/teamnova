package com.example.dayoungkim.mundan.d_viewText;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dayoungkim.mundan.MainActivity;
import com.example.dayoungkim.mundan.R;
import com.example.dayoungkim.mundan.a_registration.SessionManager;
import com.example.dayoungkim.mundan.b_writingText.ApiClient;
import com.example.dayoungkim.mundan.b_writingText.ApiInterface;
import com.example.dayoungkim.mundan.b_writingText.Note;
import com.example.dayoungkim.mundan.b_writingText.WritingTextActivity;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class PharasesFragment extends Fragment  {

    private static final int INTENT_EDIT = 200;
    View v;



    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;



    //리싸이클러뷰 선언
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;


//    MainPresenter presenter;
    MainAdapter adapter;
    MainAdapter.ItemClickListenter itemClickListenter;
    List<Note> note;
    ApiInterface apiInterface;


    //좋아요 할 때 넣음
    SessionManager sessionManager;
    String post_idx;
    TextView textView14;


    public PharasesFragment() {
        // Required empty public constructor
    }


    public static PharasesFragment newInstance(String param1, String param2) {
        PharasesFragment fragment = new PharasesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        //검색기능 할 때 getmenuInflater 안돼서 넣음
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_pharases, container, false);





        //처음 접속했을 때 전체글 깔아줌
        show_notes("notes", null);

        //처음 접속했을 때 라이크 깔아줌
        sessionManager = new SessionManager(getContext());
        HashMap<String, String> user = sessionManager.getUserDetail();
        String mId = user.get(sessionManager.ID);
        show_likes(mId);


        textView14 = v.findViewById(R.id.textView14);


        //라디오버튼 위치찾기
        RadioGroup radioGroup = v.findViewById(R.id.radioGroup);
        RadioButton ButtonAll = v.findViewById(R.id.radio_all);

        //처음 접속했을 때 라디오버튼 체크되어있게
        ButtonAll.setChecked(true);

        //라디오그룹에 체인지리스너 달아주기
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if(radioGroup.getId() == R.id.radioGroup)
                {
                    switch (i)
                    {
                        case R.id.radio_all :
                            show_notes("notes", null);
                            break;

                        case R.id.radio_essay :
                            show_notes("notes", "에세이");
                            break;

                        case R.id.radio_novel :
                            show_notes("notes", "소설");
                            break;

                        case R.id.radio_poem :
                            show_notes("notes", "시");
                            break;
                    }

                }


                //스트링 rb는 체크드id의 인트값이고 이걸 스트링값으로 바꿔서 받아옴
//                String rb = ((RadioButton) v.findViewById(i)).getText().toString();
//                show_notes("notes", rb);



            }
        });



        //리싸이클러뷰 위치찾기
        swipeRefreshLayout = v.findViewById(R.id.swipe_refresh);
        recyclerView = v.findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        swipeRefreshLayout.setOnRefreshListener(
                () -> show_notes("notes", null)
        );





        itemClickListenter = ((view, position) -> {

            int no = note.get(position).getNo();
            String notes = note.get(position).getNote();
            String user_cat = note.get(position).getCategory();

            Intent intent = new Intent(getActivity(), WritingTextActivity.class);
            intent.putExtra("no", no);
            intent.putExtra("note", notes);
            intent.putExtra("user_cat", user_cat);
            startActivityForResult(intent, INTENT_EDIT);

        });







        return v;
    }


    //라이크 보여주기
    public void show_likes (String like_id) {

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        retrofit2.Call<List<Note>> call = apiInterface.getLikes(like_id);

        call.enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Note>> call, Response<List<Note>> response) {


                note = response.body();

                Log.d("좋아염", "response" + response.body().toString());

//                textView14.setText(response.body().toString());


            }

            @Override
            public void onFailure(retrofit2.Call<List<Note>> call, Throwable t) {

            }
        });


    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        show_notes("notes", null);

//            presenter.getData();


//            이게 원래 예제 코드이긴 한데, 이프 넣으면 삭제했을 때 새로고침이 안된다...
//        if (requestCode == INTENT_EDIT && resultCode == Activity.RESULT_OK) {
//            presenter.getData();
//        }


    }


    public void show_notes (String type, String rb) {

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        retrofit2.Call<List<Note>> call = apiInterface.getNotes(rb);

        call.enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Note>> call, Response<List<Note>> response) {

                note = response.body();
                adapter = new MainAdapter(getContext(), note, itemClickListenter);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);

                Log.d("좋아염", "response" + response.body().toString());

            }

            @Override
            public void onFailure(retrofit2.Call<List<Note>> call, Throwable t)
            {

            }
        });





    }







    ///////
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }





    public interface OnFragmentInteractionListener
    {
        void onFragmentInteraction(Uri uri);
    }





}







