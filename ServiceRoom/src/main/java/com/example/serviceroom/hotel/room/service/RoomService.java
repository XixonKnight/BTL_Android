package com.example.serviceroom.hotel.room.service;

import com.example.serviceroom.hotel.room.RoomBO;
import com.example.serviceroom.hotel.room.form.RoomForm;
import com.example.serviceroom.hotel.room.repository.RoomRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoomService {
    private static final Logger log = LogManager.getLogger(RoomService.class);
    @Autowired
    private RoomRepository roomRepository;

    public List<RoomBO> getAll() {
        return roomRepository.findAll();
    }

    public boolean createRoom(RoomForm roomForm) {
        try {
            ModelMapper modelMap = new ModelMapper();
            RoomBO roomBO = modelMap.map(roomForm, RoomBO.class);
            roomBO.setGuid(UUID.randomUUID().toString());
            roomRepository.save(roomBO);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

    public boolean deleteRoom(String guid) {
        try {
            RoomBO roomBO = roomRepository.findByGuid(guid).
                    orElseThrow(() -> new Exception("Room not found - " + guid));
            roomRepository.delete(roomBO);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }


}
