package com.example.dayoungkim.mundan.f_search;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dayoungkim.mundan.R;
import com.example.dayoungkim.mundan.b_writingText.ApiClient;
import com.example.dayoungkim.mundan.b_writingText.ApiInterface;
import com.example.dayoungkim.mundan.b_writingText.Note;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchFragment extends Fragment {


    View v;


    //리싸이클러뷰 선언
    RecyclerView recyclerView;


    Adapter adapter;
    List<Note> notes;
    ApiInterface apiInterface;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SearchFragment() {
        // Required empty public constructor
    }


    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_search, container, false);



        EditText searchBox = v.findViewById(R.id.et_search);
        Button searchButton = v.findViewById(R.id.bt_search);



        recyclerView = v.findViewById(R.id.my_recycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

//        fetchNotes("notes", "");
//        //키값 없을 때




        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key = searchBox.getText().toString();
                fetchNotes("notes", key);
            }
        });









        return v;
    }

    ////////


    public void fetchNotes(String type, String key) {

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Note>> call = apiInterface.getSearch(key);

        call.enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(Call<List<Note>> call, Response<List<Note>> response) {

                notes = response.body();
                adapter = new Adapter(notes, getContext());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<List<Note>> call, Throwable t) {

                Toast.makeText(getContext(), "에러 " + t.toString(), Toast.LENGTH_SHORT).show();

            }
        });

    }






    ////////

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


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
