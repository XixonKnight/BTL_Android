package com.example.serviceroom.hotel.hotel.repository;

import com.example.serviceroom.hotel.hotel.HotelBO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HotelRepository extends JpaRepository<HotelBO,Integer> {
    Optional<HotelBO> findByGuid(String guid);

}
