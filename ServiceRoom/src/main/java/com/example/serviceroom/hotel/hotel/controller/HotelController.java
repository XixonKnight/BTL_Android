package com.example.serviceroom.hotel.hotel.controller;

import com.example.serviceroom.common.Constants;
import com.example.serviceroom.common.Response;
import com.example.serviceroom.hotel.hotel.HotelBO;
import com.example.serviceroom.hotel.hotel.hotelForm.HotelForm;
import com.example.serviceroom.hotel.hotel.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/hotel")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @GetMapping("/getAll")
    public ResponseEntity<List<HotelBO>> getAll() {
        return ResponseEntity.ok(hotelService.getAllHotel());
    }

    @PostMapping("/create-hotel")
    public Response createHotel(@RequestBody HotelForm hotelForm) {
        boolean flag = hotelService.createdHotel(hotelForm);
        if (flag) {
            return new Response(Constants.RESPONSE_TYPE.SUCCESS, Constants.RESPONSE_CODE.SUCCESS);
        }
        return new Response(Constants.RESPONSE_TYPE.ERROR, Constants.RESPONSE_CODE.ERROR);
    }

    @DeleteMapping("/deleteHotel")
    public Response deleteHotel(@RequestBody HotelForm hotelForm){
        boolean flag = hotelService.deleteHotel(hotelForm.getGuid());
        if (flag) {
            return new Response(Constants.RESPONSE_TYPE.SUCCESS, Constants.RESPONSE_CODE.SUCCESS);
        }
        return new Response(Constants.RESPONSE_TYPE.ERROR, Constants.RESPONSE_CODE.ERROR);
    }
}
