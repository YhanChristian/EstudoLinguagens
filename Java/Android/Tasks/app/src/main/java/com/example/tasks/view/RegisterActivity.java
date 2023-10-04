package com.example.tasks.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.tasks.R;
import com.example.tasks.viewmodel.RegisterViewModel;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();
    private RegisterViewModel mRegisterViewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Botão de voltar nativo
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //Mapeia elementos layout
        this.mViewHolder.editName = findViewById(R.id.edit_name);
        this.mViewHolder.editEmail = findViewById(R.id.edit_email);
        this.mViewHolder.editPassword = findViewById(R.id.edit_password);
        this.mViewHolder.buttonCreate = findViewById(R.id.button_create);

        this.mViewHolder.buttonCreate.setOnClickListener(this);

        // Incializa variáveis
        this.mRegisterViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);

        // Cria observadores
        this.loadObservers();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        /* Trata evento button create */
        if(id == R.id.button_create) {
            String name = this.mViewHolder.editName.getText().toString();
            String email = this.mViewHolder.editEmail.getText().toString();
            String password = this.mViewHolder.editPassword.getText().toString();

            this.mRegisterViewModel.createUser(name, email, password);
        }
    }

    private void loadObservers() {}


    /**
     * ViewHolder
     */
    private static class ViewHolder {
        EditText editName;
        EditText editEmail;
        EditText editPassword;
        Button buttonCreate;
    }

}