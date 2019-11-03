package com.example.dayoungkim.mundan.b_writingText;

import android.content.SharedPreferences;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Note {

    @Expose
    @SerializedName("no") private int no;

    @Expose
    @SerializedName("id") private String id;

    @Expose
    @SerializedName("nick") private String nick;

    @Expose
    @SerializedName("note") private String note;

    @Expose
    @SerializedName("category") private String category;

    @Expose
    @SerializedName("date") private String date;


    /////

    @Expose
    @SerializedName("subject") private String subject;

    @Expose
    @SerializedName("origin") private String origin;

    ////


    @Expose
    @SerializedName("success") private Boolean success;

    @Expose
    @SerializedName("message") private String message;


    ////



    @Expose
    @SerializedName("post_idx") private int post_idx;





    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    /////


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }


    ////


    public int getPost_idx() {
        return post_idx;
    }

    public void setPost_idx(int post_idx) {
        this.post_idx = post_idx;
    }



}
