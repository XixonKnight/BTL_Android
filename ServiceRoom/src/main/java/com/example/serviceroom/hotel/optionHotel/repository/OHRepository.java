package com.example.serviceroom.hotel.optionHotel.repository;

import com.example.serviceroom.hotel.optionHotel.OptionHotelBO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OHRepository extends JpaRepository<OptionHotelBO,Integer> {
    Optional<OptionHotelBO> findByGuidHotel(String guid);
}
