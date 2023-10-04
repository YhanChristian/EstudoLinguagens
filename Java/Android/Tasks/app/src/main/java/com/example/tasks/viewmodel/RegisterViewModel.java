package com.example.tasks.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.tasks.service.repository.PersonRepository;

public class RegisterViewModel extends AndroidViewModel {

private PersonRepository mPersonRepository = new PersonRepository();

    public RegisterViewModel(@NonNull Application application) {
        super(application);
    }

    public void createUser(String name, String email, String password) {
        this.mPersonRepository.createUser(name, email, password);
    }
}
