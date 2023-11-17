package com.example.tasks.service.model;

import com.google.gson.annotations.SerializedName;

public class PersonModel {
    @SerializedName("token")
    private String token;
    @SerializedName("personKey")
    private String personKey;
    @SerializedName("name")
    private String name;

    public PersonModel(String token, String personKey, String name) {
        this.token = token;
        this.personKey = personKey;
        this.name = name;
    }


    public String getToken() {
        return token;
    }
    public String getPersonKey() {
        return personKey;
    }

    public String getName() {
        return name;
    }

}
