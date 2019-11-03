package com.example.dayoungkim.mundan.b_writingText;

public interface WritingTextView {

    void showProgress();
    void hideProgress();
    void onRequestSuccess(String message);
    void onRequestError(String message);


}
