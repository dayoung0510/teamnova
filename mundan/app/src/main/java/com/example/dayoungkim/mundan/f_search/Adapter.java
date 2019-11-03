package com.example.dayoungkim.mundan.f_search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dayoungkim.mundan.R;
import com.example.dayoungkim.mundan.b_writingText.Note;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<Note> notes;
    private Context context;

    public Adapter(List<Note> notes, Context context) {
        this.notes = notes;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note_text, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tv_name.setText(notes.get(position).getNick());
        holder.tv_date.setText(notes.get(position).getDate());
        holder.tv_subject.setText(notes.get(position).getSubject());
        holder.tv_origin.setText(notes.get(position).getOrigin());
        holder.tv_content.setText(notes.get(position).getNote());
        holder.tv_category.setText(notes.get(position).getCategory());

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tv_name, tv_date, tv_subject, tv_origin, tv_content, tv_category;


        public MyViewHolder(View itemView) {
            super(itemView);



            tv_name = itemView.findViewById(R.id.textView_list_name);
            tv_date = itemView.findViewById(R.id.textView_list_date);
            tv_subject = itemView.findViewById(R.id.textView_list_subject);
            tv_origin = itemView.findViewById(R.id.textView_list_origin);
            tv_content = itemView.findViewById(R.id.textView_list_content);
            tv_category = itemView.findViewById(R.id.textView_list_category);


        }
    }
}