package com.example.appbookingroom.config;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;

import com.example.appbookingroom.R;

public class LoadingDialog extends Dialog {

    @SuppressLint("ResourceAsColor")
    public LoadingDialog(@NonNull Context context) {
        super(context);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER_HORIZONTAL;
        getWindow().setAttributes(params);
        setTitle(null);
        setCancelable(false);
        setOnCancelListener(null);
        View view = LayoutInflater.from(context).inflate(R.layout.custom_dialog, null, false);
//        LinearLayout layout = view.findViewById(R.id.layout_process_bar);
//        layout.setBackgroundColor(android.R.color.transparent);
        setContentView(view);
    }

}
