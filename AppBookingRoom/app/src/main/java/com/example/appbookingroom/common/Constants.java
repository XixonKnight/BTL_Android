package com.example.appbookingroom.common;

public class Constants {

    public static final String SUBJECT_SEND = "Booking Room";

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

//    public static final String IP_ADDRESS = "172.30.128.1";
    public static final String IP_ADDRESS = "192.168.1.11";


    public static class API {
        public static final String URL_CREATE_USER = "http://" + IP_ADDRESS + "/api/user/created";
        public static final String URL_LOGIN = "http://" + IP_ADDRESS + "/api/user/authenticate";
    }
}
