package com.example.tasks.service.repository;

import android.content.Context;

import com.example.tasks.R;
import com.example.tasks.service.constants.TaskConstants;
import com.example.tasks.service.listener.APIListener;
import com.example.tasks.service.model.PersonModel;
import com.example.tasks.service.repository.local.SecurityPreferences;
import com.example.tasks.service.repository.remote.PersonService;
import com.example.tasks.service.repository.remote.RetrofitClient;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonRepository {

    private PersonService mPersonService;
    private SecurityPreferences mSecurityPreferences;
    private Context mContext;


    public PersonRepository(Context context) {
        this.mPersonService = RetrofitClient.createService((PersonService.class));
        this.mSecurityPreferences = new SecurityPreferences(context);
        this.mContext = context;
    }

    public void createUser(String name, String email, String password) {
        Call<PersonModel> call = this.mPersonService.createUser(name, email, password, true);
        call.enqueue(new Callback<PersonModel>() {
            @Override
            public void onResponse(Call<PersonModel> call, Response<PersonModel> response) {
                PersonModel p = response.body();
                int code = response.code();
                String s = "";
            }

            @Override
            public void onFailure(Call<PersonModel> call, Throwable t) {
                String s = "";
            }
        });
    }

    public void login(String email, String password, final APIListener<PersonModel> listener) {
        Call<PersonModel> call = this.mPersonService.login(email, password);
        call.enqueue(new Callback<PersonModel>() {
            @Override
            public void onResponse(Call<PersonModel> call, Response<PersonModel> response) {
                if (response.code() == TaskConstants.HTTP.SUCCESS) {
                    listener.onSuccess(response.body());
                } else {
                    try {
                        String json = response.errorBody().string();
                        String str = new Gson().fromJson(json, String.class);
                        listener.onFailure(str);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<PersonModel> call, Throwable t) {
                listener.onFailure(mContext.getString(R.string.ERROR_UNEXPECTED));
            }
        });
    }

    public void saveUserData(PersonModel personModel) {
        this.mSecurityPreferences.storeString(TaskConstants.SHARED.TOKEN_KEY, personModel.getToken());
        this.mSecurityPreferences.storeString(TaskConstants.SHARED.PERSON_KEY, personModel.getPersonKey());
        this.mSecurityPreferences.storeString(TaskConstants.SHARED.PERSON_NAME, personModel.getName());
    }

    public PersonModel getUserData() {

        return new PersonModel(this.mSecurityPreferences.getStoredString(TaskConstants.SHARED.TOKEN_KEY),
                this.mSecurityPreferences.getStoredString(TaskConstants.SHARED.PERSON_KEY),
                this.mSecurityPreferences.getStoredString(TaskConstants.SHARED.PERSON_NAME));

    }
}
