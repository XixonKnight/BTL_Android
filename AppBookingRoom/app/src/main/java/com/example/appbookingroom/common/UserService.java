package com.example.appbookingroom.common;

import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.entity.StringEntity;

public class UserService {
    private static AsyncHttpClient client = new AsyncHttpClient();


    /**
     *
     * @param context
     * @param url
     * @param entity
     * @param responseHandler
     */
    public static void login(Context context, String url, StringEntity entity, AsyncHttpResponseHandler responseHandler) {
        client.post(context, url, entity, Constants.CONTENT_TYPE_JSON, responseHandler);
    }
    /**
     *
     * @param context
     * @param url
     * @param entity
     * @param responseHandler
     */
    public static void register(Context context, String url, StringEntity entity, AsyncHttpResponseHandler responseHandler) {
        client.post(context, url, entity, Constants.CONTENT_TYPE_JSON, responseHandler);
    }

}
