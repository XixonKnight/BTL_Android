package com.example.appbookingroom.common;

public class Constants {
    public static final String IP_ADDRESS = "172.30.128.1";
//    public static final String IP_ADDRESS = "172.30.128.1";


    public static class API {
        public static final String URL_CREATE_USER = "http://" + IP_ADDRESS + "/api/customer/created";
        public static final String URL_LOGIN = "http://" + IP_ADDRESS + "/api/customer/authenticate";
    }
}
