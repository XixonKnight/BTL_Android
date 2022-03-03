package com.example.serviceroom.hotel.hotel.bean;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class HotelBean {
    private int id;
    private String guid;
    private String guidArea;
    private String name;
    private String address;
    private Date createdDate;
}
