package com.example.dayoungkim.mundan.g_mypage;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.dayoungkim.mundan.R;
import com.example.dayoungkim.mundan.a_registration.SessionManager;
import com.example.dayoungkim.mundan.b_writingText.Note;

import java.util.List;

public class MypageAdapter extends RecyclerView.Adapter<MypageAdapter.RecyclerViewAdapter> {

    private List<Note> notes;
    private Context context;
    private ItemClickListener itemClickListener;


    ImageButton editButton, delButton;
    SessionManager sessionManager;



    public MypageAdapter(Context context, List<Note> notes, ItemClickListener itemClickListener) {
        this.notes = notes;
        this.context = context;
        this.itemClickListener = itemClickListener;
    }


    @Override
    public RecyclerViewAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note_text, parent, false);
        return new RecyclerViewAdapter(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter recyclerViewAdapter, int position) {

        Note note = notes.get(position);

        recyclerViewAdapter.tv_name.setText(notes.get(position).getNick());
        recyclerViewAdapter.tv_date.setText(notes.get(position).getDate());
        recyclerViewAdapter.tv_subject.setText(notes.get(position).getSubject());
        recyclerViewAdapter.tv_origin.setText(notes.get(position).getOrigin());
        recyclerViewAdapter.tv_content.setText(notes.get(position).getNote());
        recyclerViewAdapter.tv_category.setText(notes.get(position).getCategory());

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }












    public static class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tv_name, tv_date, tv_subject, tv_origin, tv_content, tv_category;
        CardView card_item;
        ItemClickListener itemClickListener;


        public RecyclerViewAdapter(View itemView, ItemClickListener itemClickListener) {
            super(itemView);

            this.itemClickListener = itemClickListener;


            tv_name = itemView.findViewById(R.id.textView_list_name);
            tv_date = itemView.findViewById(R.id.textView_list_date);
            tv_subject = itemView.findViewById(R.id.textView_list_subject);
            tv_origin = itemView.findViewById(R.id.textView_list_origin);
            tv_content = itemView.findViewById(R.id.textView_list_content);
            tv_category = itemView.findViewById(R.id.textView_list_category);

            ImageButton editButton = itemView.findViewById(R.id.bt_edit);
            ImageButton delButton = itemView.findViewById(R.id.bt_delete);

            this.card_item = itemView.findViewById(R.id.card_item);
            editButton.setOnClickListener(this);





        }





        @Override
        public void onClick(View view) {
            itemClickListener.onItemClick(view, getAdapterPosition());
            }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}
