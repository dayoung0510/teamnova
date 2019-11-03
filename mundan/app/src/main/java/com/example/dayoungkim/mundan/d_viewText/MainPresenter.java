//package com.example.dayoungkim.mundan.d_viewText;
//
//import android.support.annotation.NonNull;
//import android.util.Log;
//
//import com.example.dayoungkim.mundan.b_writingText.ApiClient;
//import com.example.dayoungkim.mundan.b_writingText.ApiInterface;
//import com.example.dayoungkim.mundan.b_writingText.Note;
//
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class MainPresenter {
//
//    private MainView view;
//
//    public MainPresenter(MainView view) {
//        this.view = view;
//    }
//
//
////    void getData() {
////
////        view.showLoading();
////
////        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
////
////        Call<List<Note>> call = apiInterface.getNotes();
////        call.enqueue(new Callback<List<Note>>() {
////            @Override
////            public void onResponse(@NonNull Call<List<Note>> call, @NonNull Response<List<Note>> response) {
////
////                view.hideLoading();
////                if (response.isSuccessful() && response.body() != null) {
////
////                    view.onGetResult(response.body());
////
////                }
////            }
////
////            @Override
////            public void onFailure(@NonNull Call<List<Note>> call, @NonNull Throwable t) {
////
////                view.hideLoading();
////                view.onErrorLoading(t.getLocalizedMessage());
////
////            }
////        });
////
////    }
//
//}
