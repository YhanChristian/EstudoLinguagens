package com.example.tasks.service.listener;

public class Feedback {
    private boolean mSuccess = true;
    private String mMessage = "";

    public Feedback() {

    }

    public Feedback(String str) {
        this.mMessage = str;
        this.mSuccess = false;
    }
    public boolean isSuccess(){
        return this.mSuccess;
    }
    public String getMessage() {
        return this.mMessage;
    }
}
