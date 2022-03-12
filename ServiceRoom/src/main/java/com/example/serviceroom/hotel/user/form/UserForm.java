package com.example.serviceroom.hotel.user.form;

import com.example.serviceroom.common.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserForm {
    private int id;
    private String guid;
    private String username;
    private String gmail;
    private String password;
    private String address;
    private String gender;
    private Date createdDate;
    private Date updatedDate;
    private String strDateOfBirth;
}
