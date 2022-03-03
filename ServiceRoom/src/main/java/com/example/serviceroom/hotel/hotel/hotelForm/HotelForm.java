package com.example.serviceroom.hotel.hotel.hotelForm;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class HotelForm {
    private int id;
    private String guid;
    private String guidArea;
    private String name;
    private String address;
    private Date createdDate;

}
