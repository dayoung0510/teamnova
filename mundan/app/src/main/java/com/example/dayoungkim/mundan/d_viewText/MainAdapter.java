package com.example.dayoungkim.mundan.d_viewText;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.dayoungkim.mundan.R;
import com.example.dayoungkim.mundan.ValidateLikeRequest;
import com.example.dayoungkim.mundan.a_registration.RegisterActivity;
import com.example.dayoungkim.mundan.a_registration.SessionManager;
import com.example.dayoungkim.mundan.a_registration.ValidateNickRequest;
import com.example.dayoungkim.mundan.b_writingText.ApiClient;
import com.example.dayoungkim.mundan.b_writingText.ApiInterface;
import com.example.dayoungkim.mundan.b_writingText.Note;
import com.example.dayoungkim.mundan.b_writingText.WritingTextActivity;
import com.example.dayoungkim.mundan.b_writingText.WritingTextPresenter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.RecyclerViewAdapter> {

    private Context context;
    private List<Note> notes;
    private ItemClickListenter itemClickListenter;




    ImageButton editButton, heartRed, heartWhite;
    TextView numberOflikes;

    Button dislike;

    WritingTextPresenter presenter;

    SessionManager sessionManager;

    ApiInterface apiInterface;
    List<Note> note;




    public MainAdapter(Context context, List<Note> notes, ItemClickListenter itemClickListenter) {
        this.context = context;
        this.notes = notes;
        this.itemClickListenter = itemClickListenter;
    }


    @NonNull
    @Override
    public RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_note_text, viewGroup, false);

        return new RecyclerViewAdapter(view, itemClickListenter);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter recyclerViewAdapter, int i) {
        Note note = notes.get(i);

        recyclerViewAdapter.tv_name.setText(note.getNick());
        recyclerViewAdapter.tv_date.setText(note.getDate());
        ////
        recyclerViewAdapter.tv_subject.setText(note.getSubject());
        recyclerViewAdapter.tv_category.setText(note.getCategory());
        recyclerViewAdapter.tv_origin.setText(note.getOrigin());
        ////
        recyclerViewAdapter.tv_content.setText(note.getNote());




        //만세!!! 자기한테만 수정삭제 아이콘 보이게 하기 (쉐어드 말고 세션매니저로)
        String writing_id = note.getId();
        sessionManager = new SessionManager(context);
        HashMap<String, String> user = sessionManager.getUserDetail();
        String mId = user.get(sessionManager.ID);



        if (mId.equals(writing_id))
        {
            editButton.setVisibility(View.VISIBLE);
        }
        else
        {
            editButton.setVisibility(View.INVISIBLE);
        }



        //글 idx
        int writing_no = note.getNo();
        String string_writing_no = Integer.toString(writing_no);
        int post_idx = note.getPost_idx();
        Log.d("좋아염" , " " +post_idx);






        //좋아요에 클릭리스너 달기
        heartWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d("좋아염", "" + writing_no + " / " + post_idx);

            }
        });




//좋아요 원래
//        heartWhite.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                sessionManager = new SessionManager(context);
//
//                HashMap<String, String> user = sessionManager.getUserDetail();
//                String mId = user.get(sessionManager.ID);
//
//                final String id = mId;
//                final int no = writing_no;
//
//
//                ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
//                Call<Note> call = apiInterface.clickLike(id, no);
//                call.enqueue(new Callback<Note>() {
//                    @Override
//                    public void onResponse(Call<Note> call, Response<Note> response) {
//
//                        if (response.isSuccessful() && response.body() != null) {
//                            Boolean success = response.body().getSuccess();
//
//                            if (success) {
//                                Log.d("좋아염", "5");
//                                Log.d("좋아염", id + " / " + no);
//
//                                heartWhite.setVisibility(View.GONE);
//                                heartRed.setVisibility(View.VISIBLE);
//
//                            } else {
//                                Log.d("좋아염", "6");
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<Note> call, Throwable t) {
//                        Log.d("좋아염", "7");
//                    }
//                });
//
//
//
//            }
//        });



        //좋아요 삭제
//        dislike.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final String id = mId;
//                final int no = writing_no;
//
//                ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
//                Call<Note> call = apiInterface.deleteLike(id, no);
//                call.enqueue(new Callback<Note>() {
//                    @Override
//                    public void onResponse(Call<Note> call, Response<Note> response) {
//                        if(response.isSuccessful() && response.body() != null) {
//                            Boolean success = response.body().getSuccess();
//                            if(success)
//                            {
//                                Toast.makeText(context, "삭제됨", Toast.LENGTH_SHORT).show();
//                            }
//                            else
//                            {
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<Note> call, Throwable t) {
//
//                    }
//                });
//            }
//        });


    }



    @Override
    public int getItemCount() {
        return notes.size();
    }


    class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView tv_name, tv_date, tv_subject, tv_origin, tv_content, tv_category;
        CardView card_item;
        ItemClickListenter itemClickListenter;


        RecyclerViewAdapter(@NonNull View itemView, ItemClickListenter itemClickListenter) {
            super(itemView);
            this.itemClickListenter = itemClickListenter;

            //위치찾기
            tv_name = itemView.findViewById(R.id.textView_list_name);
            tv_date = itemView.findViewById(R.id.textView_list_date);
            tv_subject = itemView.findViewById(R.id.textView_list_subject);
            tv_origin = itemView.findViewById(R.id.textView_list_origin);
            tv_content = itemView.findViewById(R.id.textView_list_content);
            tv_category = itemView.findViewById(R.id.textView_list_category);

            //수정버튼 찾기
            editButton = itemView.findViewById(R.id.bt_edit);


            //에딧버튼에 클릭리스너 달아라
            this.card_item = itemView.findViewById(R.id.card_item);
            editButton.setOnClickListener(this);


            //좋아요 버튼
            heartWhite = itemView.findViewById(R.id.likeButton);




        }




        @Override
        public void onClick(View v) {
            itemClickListenter.onItemClick(v, getAdapterPosition());
        }
    }



//    public void setLikeButtonStatus(String like_id, String no)
//    {
//
//        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
//        Call<List<Note>> call = apiInterface.getLikes(like_id);
//
//        call.enqueue(new Callback<List<Note>>() {
//            @Override
//            public void onResponse(Call<List<Note>> call, Response<List<Note>> response) {
//
//                note = response.body();
//                Log.d("좋아염", "like_id 보낸값 =" +like_id);
//                Log.d("좋아염", "response =" +response);
//
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Note>> call, Throwable t) {
//
//            }
//        });
//
//
//    }




    public interface ItemClickListenter {
        void onItemClick(View view, int position);
    }






}
