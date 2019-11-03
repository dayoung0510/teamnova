package com.example.dayoungkim.mundan.b_writingText;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WritingTextPresenter {

    private WritingTextView view;

    public WritingTextPresenter(WritingTextView view) {
        this.view = view;
    }

    //수정기능
    void saveNote(final String id, final String nick, final String note, final String category) {

        //다이얼로그 위에서 선언한 거 여기서 나와라!
        view.showProgress();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<Note> call = apiInterface.saveNote(id, nick, note, category);
        Log.d("글등록", "3" + id + nick + note);


        call.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(@NonNull Call<Note> call, @NonNull Response<Note> response) {

                view.hideProgress();

                if (response.isSuccessful() && response.body() != null) {
                    Boolean success = response.body().getSuccess();

                    if (success)
                    {
                        view.onRequestSuccess(response.body().getMessage());
                    }
                    else
                    {
                        view.onRequestError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Note> call, @NonNull Throwable t) {

                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());

            }
        });


    }


    // 수정기능
    void updateNote(int no, String note, String category)
    {

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<Note> call = apiInterface.updateNote(no, note, category);

        call.enqueue(new Callback<Note>()
        {
            @Override
            public void onResponse(@NonNull Call<Note> call, @NonNull Response<Note> response)
            {
                if (response.isSuccessful() && response.body() != null)
                {
                    Boolean success = response.body().getSuccess();

                    if(success)
                    {
                        view.onRequestSuccess(response.body().getMessage());

                    }
                    else
                    {
                        view.onRequestError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Note> call, @NonNull Throwable t)
            {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());

            }
        });

    }




    //삭제기능
    void deleteNote(int no)
    {

        view.showProgress();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Note> call = apiInterface.deleteNote(no);
        call.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(@NonNull Call<Note> call, @NonNull Response<Note> response) {

                view.hideProgress();
                if (response.isSuccessful() && response.body() != null)
                {

                    Boolean success = response.body().getSuccess();
                    if(success)
                    {
                        view.onRequestSuccess(response.body().getMessage());
                    }
                    else
                    {
                        view.onRequestError(response.body().getMessage());
                    }

                }

            }

            @Override
            public void onFailure(@NonNull Call<Note> call, @NonNull Throwable t) {

                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());

            }
        });


    }



}
