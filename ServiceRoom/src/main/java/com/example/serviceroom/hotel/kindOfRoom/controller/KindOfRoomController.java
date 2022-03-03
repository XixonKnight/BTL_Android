package com.example.serviceroom.hotel.kindOfRoom.controller;

import com.example.serviceroom.common.Constants;
import com.example.serviceroom.common.Response;
import com.example.serviceroom.hotel.kindOfRoom.KindOfRoom;
import com.example.serviceroom.hotel.kindOfRoom.form.KindOfRoomForm;
import com.example.serviceroom.hotel.kindOfRoom.service.KindOfRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/kod")
public class KindOfRoomController {
    @Autowired
    private KindOfRoomService kindOfRoomService;
    @GetMapping("/getAllKOD")
    public ResponseEntity<List<KindOfRoom>> getAllKindOfRoom(){
        return  ResponseEntity.ok(kindOfRoomService.getAll());
    }
    @PostMapping("/createKOD")
    public Response createKOD(@RequestBody KindOfRoomForm kindOfRoomForm) {
        boolean flag = kindOfRoomService.createKindOfRoom(kindOfRoomForm);
        if (flag) {
            return new Response(Constants.RESPONSE_TYPE.SUCCESS, Constants.RESPONSE_CODE.SUCCESS);
        }
        return new Response(Constants.RESPONSE_TYPE.ERROR, Constants.RESPONSE_CODE.ERROR);
    }
    @DeleteMapping("/deleteKOD")
    public Response deleteKOD(@RequestBody KindOfRoomForm kindOfRoomForm){
        boolean flag = kindOfRoomService.deleteKOD(kindOfRoomForm.getGuid());
        if (flag) {
            return new Response(Constants.RESPONSE_TYPE.SUCCESS, Constants.RESPONSE_CODE.SUCCESS);
        }
        return new Response(Constants.RESPONSE_TYPE.ERROR, Constants.RESPONSE_CODE.ERROR);
    }
}
