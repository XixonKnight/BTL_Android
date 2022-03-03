package com.example.serviceroom.hotel.room.repository;

import com.example.serviceroom.hotel.room.RoomBO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<RoomBO, Integer> {
    Optional<RoomBO> findByGuid(String guid);
}
