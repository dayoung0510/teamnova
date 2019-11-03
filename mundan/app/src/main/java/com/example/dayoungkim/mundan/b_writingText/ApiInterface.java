package com.example.dayoungkim.mundan.b_writingText;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("save.php")
    Call<Note> saveNote(
            @Field("id") String id,
            @Field("nick") String nick,
            @Field("note") String note,
            @Field("category") String category
    );


    @GET("notes.php")
    Call<List<Note>> getNotes
            ( @Query("rb") String rb);



    @FormUrlEncoded
    @POST("update.php")
    Call<Note> updateNote(
            @Field("no") int no,
            @Field("note") String note,
            @Field("category") String category
    );


    @FormUrlEncoded
    @POST("delete.php")
    Call<Note> deleteNote(
            @Field("no") int no

    );


    @GET("mynotes.php")
    Call<List<Note>> getMyNotes
            ( @Query("id") String id);


    @GET("search.php")
    Call<List<Note>> getSearch
            ( @Query("key") String keyword);





    @FormUrlEncoded
    @POST("like.php")
    Call<Note> clickLike(
            @Field("like_id") String id,
            @Field("no") int no
    );

    @FormUrlEncoded
    @POST("like_delete.php")
    Call<Note> deleteLike(
            @Field("id") String id,
            @Field("post_idx") int no
    );


    @GET("like_check.php")
    Call<List<Note>> getLikes
            ( @Query("like_id") String like_id );



}
