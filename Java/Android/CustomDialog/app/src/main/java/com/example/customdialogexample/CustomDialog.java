package com.example.customdialogexample;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

public class CustomDialog {
    private final Context context;
    private int titleResId;
    private int messageResId;
    private int imageResId;
    private int colorResId;
    private int buttonTextResId;

    private View.OnClickListener buttonClickListener;

    private CustomDialog(@NonNull Context context) {
        this.context = context;

    }

    public static class Builder {
        private final CustomDialog customDialog;


        public Builder(Context context) {
            customDialog = new CustomDialog(context);
            setTitle(R.string.custom_dialog_title);
            setMessage(R.string.custom_dialog_msg);
            setImage(R.drawable.ic_circle_notification);
            setContainerColor(R.color.blue);
            setButtonText(R.string.fechar);

        }

        public Builder setTitle(@StringRes int titleResId) {
            customDialog.titleResId = titleResId;
            return this;
        }

        public Builder setMessage(@StringRes int messageResId) {
            customDialog.messageResId = messageResId;
            return this;
        }

        public Builder setImage(@DrawableRes int imageResId) {
            customDialog.imageResId = imageResId;
            return this;
        }

        public Builder setContainerColor(@ColorRes int colorResId) {
            customDialog.colorResId = colorResId;
            return this;
        }

        public Builder setButtonText(@StringRes int buttonResId) {
            customDialog.buttonTextResId = buttonResId;
            return this;
        }

        public void show() {
            customDialog.showCustomDialog();
        }

        public void setButtonClickListener(View.OnClickListener listener) {
            customDialog.buttonClickListener = listener;
        }
    }

    private void showCustomDialog() {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom_dialog);
        if (dialog.getWindow() != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawableResource(R.drawable.custom_dialog_box);
        }

        View container = dialog.findViewById(R.id.container_custom_dialog);
        if (container != null) {
            container.setBackgroundResource(colorResId);
        }

        dialog.setCancelable(false);
        Button btn = dialog.findViewById(R.id.btn_custom_dialog);
        TextView tvTitle = dialog.findViewById(R.id.tv_custom_dialog_title);
        TextView tvMessage = dialog.findViewById(R.id.tv_custom_dialog_msg);
        ImageView img = dialog.findViewById(R.id.img_custom_dialog);

        img.setImageResource(imageResId);
        tvTitle.setText(titleResId);
        tvMessage.setText(messageResId);
        btn.setTextColor(context.getResources().getColor(colorResId));
        btn.setText(buttonTextResId);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                if (buttonClickListener != null) {
                    buttonClickListener.onClick(view);
                }
            }
        });

        dialog.show();
    }
}
