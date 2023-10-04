package com.example.tasks.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.tasks.service.repository.PersonRepository;

public class LoginViewModel extends AndroidViewModel {

    private PersonRepository mPersonRepository = new PersonRepository();

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public void login(String email, String password) {
        this.mPersonRepository.login(email, password);
    }
}
