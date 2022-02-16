package com.example.serviceroom.hotel.kindOfRoom;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "kind_of_room")
@Getter
@Setter
public class KindOfRoom {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "guid")
    private String guid;

    @Column(name = "name")
    private String name;

    @Column(name = "created_date")
    private Date createdDate;
}
