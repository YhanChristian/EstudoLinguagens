package com.example.tasks.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tasks.service.listener.APIListener;
import com.example.tasks.service.listener.Feedback;
import com.example.tasks.service.model.PersonModel;
import com.example.tasks.service.repository.PersonRepository;

public class LoginViewModel extends AndroidViewModel {

    private PersonRepository mPersonRepository;
    private MutableLiveData<Feedback> mLogin = new MutableLiveData<>();
    public LiveData<Feedback> login = this.mLogin;
    private MutableLiveData<Boolean> mUserLogged = new MutableLiveData<>();
    public LiveData<Boolean> userLogged = this.mUserLogged;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        this.mPersonRepository = new PersonRepository(application);
    }

    public void login(String email, String password) {
        this.mPersonRepository.login(email, password, new APIListener<PersonModel>() {
            @Override
            public void onSuccess(PersonModel result) {
                //Salva dados de login e informações do usuário
                mPersonRepository.saveUserData(result);
                mLogin.setValue(new Feedback());
            }

            @Override
            public void onFailure(String message) {
                mLogin.setValue(new Feedback(message));
            }
        });
    }
    public void saveUserData(PersonModel personModel) {
        this.mPersonRepository.saveUserData(personModel);
    }

    public void verifyLoggedUser() {
        PersonModel personModel = this.mPersonRepository.getUserData();
        this.mUserLogged.setValue(!(personModel.getName().isEmpty()));
    }
}
