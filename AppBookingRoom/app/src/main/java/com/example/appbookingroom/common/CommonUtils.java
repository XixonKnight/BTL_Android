package com.example.appbookingroom.common;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.appbookingroom.model.Response;
import com.google.gson.Gson;

public class CommonUtils {
    public static Response convertStringToResponse(String string) {
        Gson gson = new Gson();
        return gson.fromJson(string, Response.class);
    }

    public static TextView createdView(Context context, int urlResource) {
        TextView view = new TextView(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(50, 50);
        params.setMargins(0, 0, 0, 10);
        view.setLayoutParams(params);
        view.setBackgroundResource(urlResource);
        return view;
    }

    public static TextView createdView(Context context, int id, String action) {
        TextView view = new TextView(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        view.setText(id);
        view.setGravity(Gravity.CENTER);
        return view;
    }
}
