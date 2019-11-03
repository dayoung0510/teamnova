package com.example.dayoungkim.mundan.g_mypage;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dayoungkim.mundan.R;
import com.example.dayoungkim.mundan.a_registration.SessionManager;
import com.example.dayoungkim.mundan.a_registration.ValidateNickRequest;
import com.example.dayoungkim.mundan.b_writingText.ApiClient;
import com.example.dayoungkim.mundan.b_writingText.ApiInterface;
import com.example.dayoungkim.mundan.b_writingText.Note;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MypageFragment extends Fragment {

    private static final int INTENT_EDIT = 200;
    View v;



    private TextView id, tv_nick;
    private Button logout;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private AlertDialog dialog;


    private static String URL_EDIT = "http://115.68.222.192/edit_user_detail.php";
    private static String URL_VALI = "http://115.68.222.192/passwordValidate.php";
    private static String URL_EDIT_PASSWORD = "http://115.68.222.192/edit_user_password.php";
    private static String URL_VALI_NICK = "http://115.68.222.192/edit_user_password.php";



    int PRIVATE_MODE = 0;
    private static final String PREF_ID = "LOGIN";
    private static final String LOGIN = "IS_LOGIN";
    public static final String ID = "ID";
    public static final String NICK = "NICK";


    private boolean validate = false;

    //리싸이클러뷰 선언
    RecyclerView recyclerView;
    MypageAdapter mypageAdapter;
    MypageAdapter.ItemClickListener itemClickListener;
    List<Note> note;
    ApiInterface apiInterface;




    SessionManager sessionManager;



    private EditText edit_id, edit_nick, currentPassword, changePassword, changePassword2;







    public MypageFragment() {
        // Required empty public constructor
    }


    public static MypageFragment newInstance(String param1, String param2) {
        MypageFragment fragment = new MypageFragment();
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
        v = inflater.inflate(R.layout.fragment_mypage, container, false);


        //로그아웃때문에 세션매니저 받아옴
        sessionManager = new SessionManager(getContext());
        sessionManager.checkLogin();



        //로그아웃 버튼 위치 찾기
        logout = v.findViewById(R.id.bt_logout);

        //아이디랑 닉네임 버튼 위치 찾기
//        id = v.findViewById(R.id.id_text);
        tv_nick = v.findViewById(R.id.nick_text);


        //세션매니저로 아이디 닉네임 받아오기
        HashMap<String, String> user = sessionManager.getUserDetail();
        String mId = user.get(sessionManager.ID);
        String mNick = user.get(sessionManager.NICK);
        String mNo = user.get(sessionManager.NO);

//        id.setText(mId);
        tv_nick.setText(mNick);

        Log.d("로그인", "현재 로그인된정보 : " + mId + " / " + mNick + " / " + mNo);



        //로그아웃 버튼 눌렀을 때
        logout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sessionManager.logout();
            }
        });





        ///////

        recyclerView = v.findViewById(R.id.my_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        myNotes("notes", mId);




        //////

        Button edit_my = v.findViewById(R.id.bt_edit);
        edit_my.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //다이얼로그 나올 환경 구축
                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                //뷰 셋팅 - 만들어둔 xml파일 레이아웃인플레이터 사용해서 띄워
                View v = LayoutInflater.from(getContext()).inflate(R.layout.dialog_mypage_edit, null, false);
                //다이얼로그에 셋팅해둔 뷰 얹어라
                builder.setView(v);

                edit_id = v.findViewById(R.id.edit_id);
                edit_nick = v.findViewById(R.id.edit_nick);
                currentPassword = v.findViewById(R.id.edit_pw_current);
                changePassword = v.findViewById(R.id.edit_pw_change);
                changePassword2 = v.findViewById(R.id.edit_pw_change2);

                final Button bt_confirm = v.findViewById(R.id.bt_edit_confirm);
                final TextView bt_cancel = v.findViewById(R.id.bt_edit_cancel);

                //중복확인 버튼
                final Button bt_vali = v.findViewById(R.id.bt_vali);
                bt_vali.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String userNICK = edit_nick.getText().toString();
                        if(validate)
                        {                        }
                        //ID 값을 입력하지 않았다면
                        if(userNICK.equals("")){
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            dialog = builder.setMessage("닉네임을 입력해주세요")
                                    .setPositiveButton("OK", null)
                                    .create();
                            dialog.show();
                            return;
                        }

                        //검증시작
                        com.android.volley.Response.Listener<String> responseListener2 = new com.android.volley.Response.Listener<String>(){

                            @Override
                            public void onResponse(String response2) {
                                try{
                                    JSONObject jsonResponse2 = new JSONObject(response2);
                                    boolean success = jsonResponse2.getBoolean("success");
                                    if(success){//사용할 수 있는 아이디라면

                                        edit_nick.setEnabled(false);//아이디값을 바꿀 수 없도록 함
                                        validate = true;//검증완료
                                        edit_nick.setTextColor(getResources().getColor(R.color.colorGray));
                                        bt_vali.setBackgroundColor(getResources().getColor(R.color.colorGray));
                                    }
                                    else{//사용할 수 없는 아이디라면
                                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                        dialog = builder.setMessage("이미 존재하는 닉네임입니다.")
                                                .setNegativeButton("OK", null)
                                                .create();
                                        dialog.show();
                                    }
                                }
                                catch(Exception e){
                                    e.printStackTrace();
                                }
                            }
                        };//Response.Listener 완료

                        //Volley 라이브러리를 이용해서 실제 서버와 통신을 구현하는 부분 - 아이디 중복체크 버튼 눌렀을 때
                        ValidateNickRequest validateNickRequest = new ValidateNickRequest(userNICK, responseListener2);
                        RequestQueue queue2 = Volley.newRequestQueue(getContext());
                        queue2.add(validateNickRequest);

                    }
                });



                HashMap<String, String> user = sessionManager.getUserDetail();
                String mId = user.get(sessionManager.ID);
                String mNick = user.get(sessionManager.NICK);
                String mNo = user.get(sessionManager.NO);



                edit_id.setText(mId);
                edit_nick.setText(mNick);




                //디테일 다이얼로그 크리에이트
                final AlertDialog dialog = builder.create();


                //확인버튼 클릭했을 때
                bt_confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        String string_changedNick = edit_nick.getText().toString();
                        String string_currentPassword = currentPassword.getText().toString();
                        String string_changePassword = changePassword.getText().toString();
                        String string_changePassword2 = changePassword2.getText().toString();



                        ///////////////////////////////////////

                        //현재비번이 공란이 아니면
                        if(string_currentPassword != "")
                        {

                            //현재비번 검증함
                            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_VALI,
                                    new com.android.volley.Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {

                                            try {
                                                JSONObject jsonObject = new JSONObject(response);

                                                String success = jsonObject.getString("success");
                                                JSONArray jsonArray = jsonObject.getJSONArray("login");

                                                //현재 비번이 올바르면
                                                if (success.equals("1"))
                                                {
                                                    for (int i = 0; i < jsonArray.length(); i++)
                                                    {


                                                        //비번확인칸과 재확인칸이 공란이 아니면
                                                        if(!string_changePassword.equals("") || !string_changePassword2.equals(""))
                                                        {


                                                            //확인과 재확인 텍스트가 같으면
                                                            if(string_changePassword.equals(string_changePassword2))
                                                            {
                                                                ChangePassword();
                                                                Log.d("로직", "***체인지패스워드");
                                                            }

                                                            //확인과 재확인이 틀리면
                                                            else
                                                            {
                                                                changePassword2.setError("다시 확인해주세요.");
                                                                return;
                                                            }

                                                        }

                                                        ChangeNick();
                                                        Log.d("로직", "***체인지닉네임");
                                                        dialog.dismiss();

                                                    }


                                                }

                                                //현재 비번이 틀리면
                                                else {
                                                    currentPassword.setError("다시 확인해주세요.");
                                                    return;
                                                }

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }}},

                                    new com.android.volley.Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                        }
                                    })
                            {
                                @Override
                                protected Map<String, String> getParams() throws AuthFailureError {

                                    Map<String, String> params = new HashMap<>();
                                    params.put("id", mId);
                                    params.put("password", string_currentPassword);

                                    return params;

                                }
                            };
                            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                            requestQueue.add(stringRequest);




                        }

                        }


                });


                //수정 다이얼로그에서 취소버튼 클릭했을 때
                bt_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                //디테일 다이얼로그 나와라! 이걸 해줘야 다이얼로그가 실행된다
                dialog.show();


            }
        });




        itemClickListener = ((view, i)
                -> {


                int no = note.get(i).getNo();
                String date = note.get(i).getDate();
                String content = note.get(i).getNote();
                String nick = note.get(i).getNick();


                Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();



//                String notes = notes.get(position).getNote();
//                Intent intent = new Intent(getActivity(), WritingTextActivity.class);
//                intent.putExtra("no", no); intent.putExtra("note", notes);
//                startActivityForResult(intent, INTENT_EDIT);
        });




        /////



        return v;
    }


    //////


    public void myNotes(String type, String id) {

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Note>> call = apiInterface.getMyNotes(id);
        Log.d("마이노트", " : " + id);

        call.enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(Call<List<Note>> call, Response<List<Note>> response) {

                note = response.body();
                mypageAdapter = new MypageAdapter(getActivity(), note, itemClickListener);
                recyclerView.setAdapter(mypageAdapter);
                mypageAdapter.notifyDataSetChanged();


            }

            @Override
            public void onFailure(Call<List<Note>> call, Throwable t) {

            }
        });


    }


    //원래 현재 비밀번호 검증하는 과정 + 비밀번호 바꾸기로 가라는 명령(까지만) 이었음
    //////////////
    ////id 랑 password 는 잘 들어가는데, 캐릭터가 0이라면서 로그 5번까지만 실행됨
//    private void ValiInfo(final String id, final String password) {
//
////        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_VALI,
////                new com.android.volley.Response.Listener<String>() {
////                    @Override
////                    public void onResponse(String response) {
////
////                        try {
////                            JSONObject jsonObject = new JSONObject(response);
////
////                            String success = jsonObject.getString("success");
////                            JSONArray jsonArray = jsonObject.getJSONArray("login");
////
////                            if (success.equals("1")) {
////
////                                for (int i = 0; i < jsonArray.length(); i++)
////                                {
////                                    JSONObject object = jsonArray.getJSONObject(i);
////
////                                    //비번 맞으면 아래 ChangeNick(=닉네임 바꾸는 로직) 실행하세요
////                                    ChangeNick();
////
////                                }
////
////                            }
////
////                            //비번 틀렸을 때
////                            else {
////                                Toast.makeText(getContext(), "현재 비밀번호를 다시 확인해주세요.", Toast.LENGTH_SHORT).show();
////                            }
////
////                        } catch (JSONException e) {
////                            e.printStackTrace();
////                        }
////                    }
////                },
////
////                new com.android.volley.Response.ErrorListener() {
////                    @Override
////                    public void onErrorResponse(VolleyError error) {
////                    }
////                })
////        {
////            @Override
////            protected Map<String, String> getParams() throws AuthFailureError {
////
////                Map<String, String> params = new HashMap<>();
////
////                params.put("id", id);
////                params.put("password", password);
////
////                return params;
////            }
////        };
////
////        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
////        requestQueue.add(stringRequest);
//
//    }



    //비밀번호 바꾸는 로직
    private void ChangePassword() {


        HashMap<String, String> user = sessionManager.getUserDetail();
        String mId = user.get(sessionManager.ID);
        String mNo = user.get(sessionManager.NO);

        String string_currentPassword = currentPassword.getText().toString();
        String string_changePassword = changePassword.getText().toString();



        //바뀐 내용 저장하기
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_EDIT_PASSWORD,
                new com.android.volley.Response.Listener<String>() {
                    @Override

                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(getContext(), "정보가 수정되었습니다.", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), "실패" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    } },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "실패2" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("password", string_changePassword);
                params.put("no", mNo);


                //이거 한 줄 때문에 안바뀌었다... 서버에 제대로 보냈는데 서버에서 받은건 자꾸 null값이라 떴다 (아이디랑 넘버가)
                //원래 잘못된 코드는 return super.getParams(); 이었음
                return params;
            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);





    }




    //닉네임 바꾸는 로직
    private void ChangeNick () {

        HashMap<String, String> user = sessionManager.getUserDetail();
        String mId = user.get(sessionManager.ID);
        String mNick = user.get(sessionManager.NICK);
        String mNo = user.get(sessionManager.NO);


        String string_changedNick = edit_nick.getText().toString();



        //바뀐 내용 저장하기
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_EDIT,
                new com.android.volley.Response.Listener<String>() {
            @Override

            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");

                    if (success.equals("1")) {
                        sessionManager.createSession(mId, string_changedNick, mNo);

                        HashMap<String, String> user = sessionManager.getUserDetail();
                        String mId = user.get(sessionManager.ID);
                        String mNick = user.get(sessionManager.NICK);
                        String mNo = user.get(sessionManager.NO);

                        tv_nick.setText(mNick);

                        Toast.makeText(getContext(), "정보가 수정되었습니다.", Toast.LENGTH_SHORT).show();


                        }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "실패" + e.toString(), Toast.LENGTH_SHORT).show();
                    }
            } },
                new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "실패2" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nick", string_changedNick);
                params.put("no", mNo);


                //이거 한 줄 때문에 안바뀌었다... 서버에 제대로 보냈는데 서버에서 받은건 자꾸 null값이라 떴다 (아이디랑 넘버가)
                //원래 잘못된 코드는 return super.getParams(); 이었음
                return params;
            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);




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
