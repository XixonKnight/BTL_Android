package com.example.appbookingroom.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.appbookingroom.model.Response;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.entity.StringEntity;

public class CommonUtils {
    /**
     * @param string
     * @return
     */
    public static Response convertStringToResponse(String string) {
        Gson gson = new Gson();
        return gson.fromJson(string, Response.class);
    }

    /**
     * @param context
     * @param urlResource
     * @return
     */
    public static TextView createdView(Context context, int urlResource) {
        TextView view = new TextView(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(50, 50);
        params.setMargins(0, 0, 0, 10);
        view.setLayoutParams(params);
        view.setBackgroundResource(urlResource);
        return view;
    }

    /**
     * @param context
     * @param id
     * @param action
     * @return
     */
    public static TextView createdView(Context context, int id, String action) {
        TextView view = new TextView(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        view.setText(id);
        view.setGravity(Gravity.CENTER);
        return view;
    }

    /**
     * @param sharedPreferences
     * @param key
     * @param value
     */
    public static void saveValueToSharedPreferences(SharedPreferences sharedPreferences, String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * @param sharedPreferences
     * @param key
     * @param value
     */
    public static void saveValueToSharedPreferences(SharedPreferences sharedPreferences, String key, boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * @param sharedPreferences
     * @param key
     */
    public static void removeValueFromSharedPreferences(SharedPreferences sharedPreferences, String key) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        editor.apply();
    }

    public static StringEntity convertObjectToStringEntity(Object object) {
        try {
            String strObj = new Gson().toJson(object);
            JSONObject jsonObject = new JSONObject(strObj);
            return new StringEntity(jsonObject.toString());
        } catch (JSONException | UnsupportedEncodingException e) {
            Log.e("ConvertObjToStringEntity", e.getMessage(), e);
        }
        return null;
    }

}
