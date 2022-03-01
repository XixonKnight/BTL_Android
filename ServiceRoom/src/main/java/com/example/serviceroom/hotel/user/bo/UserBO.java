package com.example.serviceroom.hotel.user.bo;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "User")
@Getter
@Setter
public class UserBO {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "guid")
    private String guid;

    @Column(name = "username")
    private String username;

    @Column(name = "gmail")
    private String gmail;

    @Column(name = "password")
    private String password;

    @Column(name = "address")
    private String address;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "gender")
    private String gender;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;
}
