package com.example.appbookingroom.common;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class UserService {
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void login(String url, RequestParams params, AsyncHttpResponseHandler responseHandler){
            client.post(url,params,responseHandler);
    }
}
