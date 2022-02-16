package com.example.serviceroom.hotel.optionHotel;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "option_hotel")
@Getter
@Setter
public class OptionHotelBO {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "guid_hotel")
    private String guidHotel;

    @Column(name = "option_name")
    private String optionName;
}
