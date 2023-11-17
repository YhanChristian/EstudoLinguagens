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

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonRepository extends BaseRepository {

    private PersonService mPersonService;
    private SecurityPreferences mSecurityPreferences;


    public PersonRepository(Context context) {
        super(context);
        this.mPersonService = RetrofitClient.createService((PersonService.class));
        this.mSecurityPreferences = new SecurityPreferences(context);
        this.mContext = context;
    }

    public void createUser(String name, String email, String password, final APIListener<PersonModel> listener) {
        Call<PersonModel> call = this.mPersonService.createUser(name, email, password, true);
        call.enqueue(new Callback<PersonModel>() {
            @Override
            public void onResponse(Call<PersonModel> call, Response<PersonModel> response) {
                if (response.code() == TaskConstants.HTTP.SUCCESS) {
                    listener.onSuccess(response.body());
                } else {
                    listener.onFailure(handleFailure(response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<PersonModel> call, Throwable t) {
                listener.onFailure(mContext.getString(R.string.ERROR_UNEXPECTED));
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
                    listener.onFailure(handleFailure(response.errorBody()));
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
