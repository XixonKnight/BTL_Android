package com.example.serviceroom.hotel.kindOfRoom.repository;

import com.example.serviceroom.hotel.kindOfRoom.KindOfRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KindOfRoomRepository extends JpaRepository<KindOfRoom,Integer> {
    Optional<KindOfRoom> findByGuid(String guid);
}
