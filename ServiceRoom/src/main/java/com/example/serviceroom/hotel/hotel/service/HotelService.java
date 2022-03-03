package com.example.serviceroom.hotel.hotel.service;

import com.example.serviceroom.hotel.hotel.HotelBO;
import com.example.serviceroom.hotel.hotel.hotelForm.HotelForm;
import com.example.serviceroom.hotel.hotel.repository.HotelRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelService {
    private static final Logger log = LogManager.getLogger(HotelService.class);
    @Autowired
    private HotelRepository hotelRepository;


    public boolean createdHotel(HotelForm hotelForm) {
        try {
            ModelMapper modelMap = new ModelMapper();
            HotelBO hotelBO = modelMap.map(hotelForm, HotelBO.class);
            hotelBO.setGuid(UUID.randomUUID().toString());
            hotelRepository.save(hotelBO);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

    public List<HotelBO> getAllHotel() {
            return hotelRepository.findAll();
    }


    public boolean deleteHotel(String guid) {
        try {
            HotelBO hotelBO = hotelRepository.findByGuid(guid).
                    orElseThrow(() -> new Exception("Hotel not found - " + guid));
            hotelRepository.delete(hotelBO);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

}
