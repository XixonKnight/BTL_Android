package com.example.serviceroom.hotel.optionHotel.service;

import com.example.serviceroom.hotel.optionHotel.OptionHotelBO;
import com.example.serviceroom.hotel.optionHotel.repository.OHRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OHService {
    private static final Logger log = LogManager.getLogger(OHService.class);

    @Autowired
    private OHRepository ohRepository;

    public List<OptionHotelBO> getAll() {
        return ohRepository.findAll();
    }

}
