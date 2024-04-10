package com.example.meuprimeiroappdio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var button : Button
    private lateinit var editEmail : EditText
    private lateinit var editPassword : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editEmail = findViewById(R.id.edit_text_email)
        editPassword = findViewById(R.id.edit_text_password)
        button = findViewById(R.id.button_login)

        button.setOnClickListener { login() }
    }

    private fun login() {
        if(editEmail.text.toString().isNotEmpty() && editPassword.text.toString().isNotEmpty()) {
            Toast.makeText(this, R.string.login_success, Toast.LENGTH_SHORT).show()
            return;
        }
        Toast.makeText(this, R.string.login_failed, Toast.LENGTH_SHORT).show()
    }
}