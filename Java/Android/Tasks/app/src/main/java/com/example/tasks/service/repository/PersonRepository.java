package com.example.tasks.service.repository;

import com.example.tasks.service.model.PersonModel;
import com.example.tasks.service.repository.remote.PersonService;
import com.example.tasks.service.repository.remote.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonRepository {

    private PersonService mPersonService;

    public  PersonRepository() {
        this.mPersonService = RetrofitClient.createService((PersonService.class));
    }

    public void createUser(String name, String email, String password) {
        Call<PersonModel> call = this.mPersonService.createUser(name, email, password, true );
        call.enqueue(new Callback<PersonModel>() {
            @Override
            public void onResponse(Call<PersonModel> call, Response<PersonModel> response) {
                PersonModel p = response.body();
                int code = response.code();
                String s = "";
                //response.errorBody();
            }

            @Override
            public void onFailure(Call<PersonModel> call, Throwable t) {
                String s = "";
            }
        });
    }

    public void login(String email, String password) {
        Call<PersonModel> call = this.mPersonService.login(email, password);
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
}
