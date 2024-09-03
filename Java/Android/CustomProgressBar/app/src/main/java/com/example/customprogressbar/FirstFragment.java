package com.example.customprogressbar;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

public class FirstFragment extends Fragment {

    private static final String TAG = "FirstFragment";

    boolean isShowProgressBar = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        if (getArguments() != null) {
            isShowProgressBar = getArguments().getBoolean("isShowProgressBar");
            Log.d(TAG, "isShowProgressBar: " + isShowProgressBar);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(isShowProgressBar) {
            setViewCustomProgress(view);
            hideButtons();
        } else {
            removeViewCustomProgress(view);
            showButtons();
        }
    }

    private void setViewCustomProgress(@NonNull View view) {
        FrameLayout parentView = view.findViewById(R.id.container_first_fragment);
        View progressBar = showProgressBar(parentView);
        parentView.addView(progressBar);
    }

    private void removeViewCustomProgress(@NonNull View view) {
        FrameLayout parentView = view.findViewById(R.id.container_first_fragment);
        parentView.removeAllViews();
    }


    private View showProgressBar(ViewGroup parentView) {
        return LayoutInflater.from(getContext()).inflate(R.layout.custom_progress_bar, parentView, false);
    }

    private void showButtons() {
        Button btnNext = requireView().findViewById(R.id.btn_next_first_fragment);
        Button btnBack = requireView().findViewById(R.id.btn_back_first_fragment);

        btnNext.setVisibility(View.VISIBLE);
        btnBack.setVisibility(View.VISIBLE);


        btnNext.setOnClickListener(v -> {
            Toast.makeText(requireContext(), "Next", Toast.LENGTH_SHORT).show();
        });

        btnBack.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });

    }

    private void hideButtons() {
        Button btnNext = requireView().findViewById(R.id.btn_next_first_fragment);
        Button btnBack = requireView().findViewById(R.id.btn_back_first_fragment);

        btnNext.setVisibility(View.GONE);
        btnBack.setVisibility(View.GONE);
    }
}