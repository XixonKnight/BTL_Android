package com.example.appbookingroom.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;

import com.example.appbookingroom.R;
import com.example.appbookingroom.model.Response;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

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


    @SuppressLint("ResourceAsColor")
    public static AppCompatButton createdButton(Context context, String id, String text){
        AppCompatButton btn = new AppCompatButton(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(20,0,0,0);
        btn.setLayoutParams(params);
        btn.setText(text);
        btn.setTag(id);
        btn.setBackgroundResource(R.drawable.style_edit_text_search);
        btn.setElevation(4);
        btn.setTextColor(R.color.black);
        btn.setPadding(50,30,50,30);
        btn.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 10);
        btn.setMinHeight(0);
        btn.setMinWidth(0);
        btn.setMinimumWidth(0);
        btn.setMinimumHeight(0);
        return btn;
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

    public static <T> List<T> convertJsonToObject(String json, Class<T> clazz) {
        try {
            T[] result = (T[]) new Gson().fromJson(json, clazz);
            return Arrays.asList(result);
        } catch (JsonSyntaxException e) {
            Log.d("convertJsonToObject", e.getMessage(),e);
        }
        return null;
    }


}
