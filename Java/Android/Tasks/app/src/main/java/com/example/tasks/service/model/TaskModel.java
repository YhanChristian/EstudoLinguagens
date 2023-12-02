package com.example.tasks.service.model;

import com.google.gson.annotations.SerializedName;

public class TaskModel {
    @SerializedName("id")
    private int mId;
    @SerializedName("PriorityId")
    private int mPriorityId;
    @SerializedName("Description")
    private String mDescription;
    @SerializedName("DueDate")
    private String mDueDate;
    @SerializedName("Complete")
    private Boolean mComplete;

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public int getmPriorityId() {
        return mPriorityId;
    }

    public void setmPriorityId(int mPriorityId) {
        this.mPriorityId = mPriorityId;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmDueDate() {
        return mDueDate;
    }

    public void setmDueDate(String mDueDate) {
        this.mDueDate = mDueDate;
    }

    public Boolean getmComplete() {
        return mComplete;
    }

    public void setmComplete(Boolean mComplete) {
        this.mComplete = mComplete;
    }
}
