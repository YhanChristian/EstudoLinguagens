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
}
