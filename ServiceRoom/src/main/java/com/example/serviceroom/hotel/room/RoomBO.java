package com.example.serviceroom.hotel.room;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "room")
@Getter
@Setter
public class RoomBO {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "guid")
    private String guid;

    @Column(name = "guid_kind_of_room")
    private String guidKindOfRoom;

    @Column(name = "guid_hotel")
    private String guidHotel;

    @Column(name = "price")
    private Double price;

    @Column(name = "created_date")
    private Date created_date;
}
