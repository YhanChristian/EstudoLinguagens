package com.example.tasks.service.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "priority")
public class PriorityModel {
    @SerializedName("id")
    @ColumnInfo(name = "id")
    @PrimaryKey
    private int mId;
    @SerializedName("Description")
    @ColumnInfo(name = "description")
    private String mDescription;

    public void setId(int id) {
        this.mId = id;
    }
    public int getId() {
        return this.mId;
    }
    public void setDescription(String description) {
        this.mDescription = description;
    }
    public String getDescription() {
        return this.mDescription;
    }
}
