package com.example.serviceroom.hotel.image;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "image")
@Getter
@Setter
public class ImageBO {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "guid")
    private String guid;

    @Column(name = "guid_room")
    private String guidRoom;

    @Column(name = "guid_hotel")
    private String guidHotel;

    @Column(name = "guid_user")
    private String guidUser;

    @Column(name = "url_image")
    private String urlImage;
}
