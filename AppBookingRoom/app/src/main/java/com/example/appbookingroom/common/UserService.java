package com.example.appbookingroom.common;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.entity.StringEntity;
import okhttp3.Cookie;

public class UserService {
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void login(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(url, params, responseHandler);
    }

    public static void register(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
//        client.post(url,params,responseHandler);
//        client.post()
    }

    public static void register(Context context, String url, StringEntity entity, AsyncHttpResponseHandler responseHandler) {
        client.post(context, url, entity, Constants.CONTENT_TYPE_JSON, responseHandler);
    }

//    public static void login(String url, Cookie cookie, AsyncHttpResponseHandler responseHandler){
//        client.post(getApp,responseHandler);
//    }

}
