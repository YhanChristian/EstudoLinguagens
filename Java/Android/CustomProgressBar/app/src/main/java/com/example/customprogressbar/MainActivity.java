package com.example.customprogressbar;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnShowProgressBar = findViewById(R.id.btn_show_custom_progress_bar);
        Button btnHideProgressBar = findViewById(R.id.btn_hide_custom_progress_bar);

        btnShowProgressBar.setOnClickListener(v -> {
            replaceFragment(new FirstFragment(), true);
        });

        btnHideProgressBar.setOnClickListener(v -> {
            replaceFragment(new FirstFragment(), false);
        });

    }

    private void replaceFragment(Fragment fragment, Boolean isShowProgressBar) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putBoolean("isShowProgressBar", isShowProgressBar);
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.main_container, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}