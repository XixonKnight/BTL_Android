package com.example.serviceroom.hotel.user.bean;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserBean {
    private int id;
    private String guid;
    private String username;
    private String gmail;
    private String password;
    private String address;
    private Date dateOfBirth;
    private String gender;
    private Date createdDate;
    private Date updatedDate;
}
