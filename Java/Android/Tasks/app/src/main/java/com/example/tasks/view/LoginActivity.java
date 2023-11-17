package com.example.tasks.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tasks.R;
import com.example.tasks.service.listener.Feedback;
import com.example.tasks.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private LoginViewModel mLoginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Mapeia elementos layout
        this.mViewHolder.editEmail = findViewById(R.id.edit_email);
        this.mViewHolder.editPassword = findViewById(R.id.edit_password);
        this.mViewHolder.buttonLogin = findViewById(R.id.button_login);

        // Incializa as variáveis
        this.mLoginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        this.setListeners();

        // Cria observadores
        this.loadObservers();

        //Verifica se usuário esta logado
        this.verifyLoggedUser();
    }

    private void verifyLoggedUser() {
        this.mLoginViewModel.verifyLoggedUser();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.button_login) {
            String email = this.mViewHolder.editEmail.getText().toString();
            String password = this.mViewHolder.editPassword.getText().toString();

            this.mLoginViewModel.login(email, password);
        }
    }

    private void setListeners() {
        this.mViewHolder.buttonLogin.setOnClickListener(this);
    }

    private void loadObservers() {
        this.mLoginViewModel.login.observe(this, new Observer<Feedback>() {
            @Override
            public void onChanged(Feedback feedback) {
                if (feedback.isSuccess()) {
                    startMainActivity();
                } else {
                    Toast.makeText(getApplicationContext(), feedback.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        this.mLoginViewModel.userLogged.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean logged) {
                if (logged) {
                    startMainActivity();
                }
            }
        });
    }

    private void startMainActivity() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * ViewHolder
     */
    private static class ViewHolder {
        EditText editEmail;
        EditText editPassword;
        Button buttonLogin;
    }
}