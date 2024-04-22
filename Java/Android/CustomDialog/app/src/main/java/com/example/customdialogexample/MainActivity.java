package com.example.customdialogexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnCustomDialog = findViewById(R.id.btn_show_custom_dialog);
        Button btnCustomErrorDialog = findViewById(R.id.btn_custom_error_dialog);
        Button btnCustomAlert = findViewById(R.id.btm_custom_alert_dialog);
        btnCustomDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomDialog.Builder builder = new CustomDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.custom_dialog_title)
                        .setMessage(R.string.custom_dialog_msg)
                        .setImage(R.drawable.ic_circle_notification)
                        .setButtonClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(MainActivity.this, "Click!", Toast.LENGTH_SHORT).show();
                            }
                        });
                builder.show();


            }
        });

        btnCustomAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomDialog.Builder builder = new CustomDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.custom_alert_dialog_title)
                        .setMessage(R.string.custom_alert_dialog_msg)
                        .setImage(R.drawable.ic_alert_notify)
                        .setContainerColor(R.color.yellow)
                        .setButtonText(R.string.custom_alert_dialog_btn)
                        .setButtonClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(MainActivity.this, "Click Alert!", Toast.LENGTH_SHORT).show();
                            }
                        });
                builder.show();
            }
        });

        btnCustomErrorDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomDialog.Builder builder = new CustomDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.custom_error_dialog_title)
                        .setMessage(R.string.custom_error_dialog_msg)
                        .setImage(R.drawable.ic_error)
                        .setContainerColor(R.color.red)
                        .setButtonText(R.string.custom_error_dialog_btn)
                        .setButtonClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(MainActivity.this, "Erro Fatal!", Toast.LENGTH_SHORT).show();
                                try {
                                    Thread.sleep(3000);
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                                System.exit(0);
                            }
                        });
                builder.show();

            }
        });
    }
}