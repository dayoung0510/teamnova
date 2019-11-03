package com.example.dayoungkim.mundan;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.dayoungkim.mundan.b_writingText.WritingTextActivity;


public class HomeFragment extends Fragment {

    View v;

    private ImageButton writingText, writingImage;
    private static final int INTENT_ADD = 100;



    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;




    public HomeFragment() {
        // Required empty public constructor
    }




    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
                             Bundle savedInstanceState)
    {
        v = inflater.inflate(R.layout.fragment_home, container, false);

        final ImageButton writingText = (ImageButton) v.findViewById(R.id.writing_text);
        final ImageButton writingImage = (ImageButton) v.findViewById(R.id.writing_image);

        //첫번째 버튼 누르면 라이팅텍스트로 이동
        writingText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent text_intent = new Intent(getContext(), WritingTextActivity.class);
                startActivityForResult(text_intent, INTENT_ADD);
            }
        });

        writingImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        return v;
    }


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
