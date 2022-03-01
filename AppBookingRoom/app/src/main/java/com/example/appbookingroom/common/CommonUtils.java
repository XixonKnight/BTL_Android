package com.example.appbookingroom.common;

import com.example.appbookingroom.model.Response;
import com.google.gson.Gson;

public class CommonUtils {
    public static Response convertStringToResponse(String string){
        Gson gson = new Gson();
        return  gson.fromJson(string,Response.class);
    }
}
