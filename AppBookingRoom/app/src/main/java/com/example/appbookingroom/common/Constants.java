package com.example.appbookingroom.common;

public class Constants {

    public static final String SUBJECT_SEND = "Booking Room";
    public static final String CONTENT_TYPE_JSON = "application/json";

    public static class RESPONSE_CODE {
        public static final String SUCCESS = "success";
        public static final String DELETE_SUCCESS = "deleteSuccess";
        public static final String UPDATE_STATUS_SUCCESS = "updateStatusSuccess";
        public static final String UPDATE_SUCCESS = "updateSuccess";
        public static final String ERROR = "error";
        public static final String WARNING = "warning";
        public static final String RECORD_DELETED = "record.deleted";
        public static final String EMAIL_ADDRESS_DELETED = "emailAddress.deleted";
        public static final String RECORD_INUSED = "record.inUsed";
        public static final String DOCUMENT_TYPE_EXISTED = "documentTypeExits";
        public static final String PARAMETER_USED = "parameterUsed";
    }

    public static final float DEFAULT_ZOOM = 15;

    public static class ACTION {
        public static final String CREATED_ICON = "CREATED_ICON";
        public static final String CREATED_TEXT = "CREATED_TEXT";
    }

    public static class MESSAGE {
        public static final String LOGIN_FAIL = "Login fail";
        public static final String REGISTER_FAIL = "Register fail";
        public static final String REGISTER_SUCCESS = "Register success";
        public static final String CREATED_TEXT = "CREATED_TEXT";
        public static final String ERROR_USERNAME = "Username invalid";
        public static final String ERROR_PASSWORD = "Password invalid";
        public static final String ERROR_EMAIL = "Email invalid";
        public static final String ERROR_GENDER = "Gender invalid";
        public static final String ERROR_ADDRESS = "Address invalid";
        public static final String ERROR_DOB = "Date of birth invalid";
    }

    public static class GOOGLE {
        public static final String CONTACTS_SCOPE = "https://www.googleapis.com/auth/contacts.readonly";
        // Bundle key for account object
        public static final String KEY_ACCOUNT = "key_account";
        public static final int RC_SIGN_IN = 9001;
        public static final int RC_RECOVERABLE = 9002;
        public static final int RC_GET_TOKEN = 9003;
        public static final String CLIENT_ID = "260452948395-i6107ekmtfvg6lt91o5aijjl7t11bq9q.apps.googleusercontent.com";
        public static final String CLIENT_ID_WEB = "260452948395-2d7tf3rpodggmjvf9a8sp350rlubaitt.apps.googleusercontent.com";


    }

    public static class KEY {
        public static final String USERNAME = "USERNAME";
        public static final String PASSWORD = "PASSWORD";
        public static final String REMEMBER = "REMEMBER";
        public static final String ACCOUNT = "ACCOUNT";
        public static final String TOKEN = "TOKEN";

    }

    //    public static final String IP_ADDRESS = "172.30.128.1";
//    public static final String IP_ADDRESS = "192.168.1.11";
    public static final String IP_ADDRESS = "192.168.0.103";
    public static final String FORMAT_DATE = "yyyy-MM-dd";


    public static class API {
        public static final String URL_CREATE_USER = "http://" + IP_ADDRESS + "/api/user/created";
        public static final String URL_LOGIN = "http://" + IP_ADDRESS + "/api/user/authenticate";
    }
}
