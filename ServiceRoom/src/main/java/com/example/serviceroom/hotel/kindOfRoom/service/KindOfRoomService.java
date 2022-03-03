package com.example.serviceroom.hotel.kindOfRoom.service;

import com.example.serviceroom.hotel.kindOfRoom.KindOfRoom;
import com.example.serviceroom.hotel.kindOfRoom.form.KindOfRoomForm;
import com.example.serviceroom.hotel.kindOfRoom.repository.KindOfRoomRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class KindOfRoomService {
    private static final Logger log = LogManager.getLogger(KindOfRoomService.class);

    @Autowired
    private KindOfRoomRepository kindOfRoomRepository;

    public List<KindOfRoom> getAll() {
        return kindOfRoomRepository.findAll();
    }

    public boolean createKindOfRoom(KindOfRoomForm kindOfRoomForm) {
        try {
            ModelMapper modelMap = new ModelMapper();
            KindOfRoom kindOfRoom = modelMap.map(kindOfRoomForm, KindOfRoom.class);
            kindOfRoom.setGuid(UUID.randomUUID().toString());
            kindOfRoomRepository.save(kindOfRoom);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

    public boolean deleteKOD(String guid) {
        try {
            KindOfRoom kindOfRoom = kindOfRoomRepository.findByGuid(guid).
                    orElseThrow(() -> new Exception("Kind of room not found - " + guid));
            kindOfRoomRepository.delete(kindOfRoom);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }
}
