package br.com.tiacademy.hotelAcademy.controller;

import java.util.List;
import br.com.tiacademy.hotelAcademy.dto.RoomDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.tiacademy.hotelAcademy.core.crud.CrudController;
import br.com.tiacademy.hotelAcademy.model.Room;
import br.com.tiacademy.hotelAcademy.service.RoomService;

@RestController
@RequestMapping("/quartos")
public class RoomController extends CrudController<Room, Long> {

    @Autowired
    private RoomService roomService;

    @GetMapping("/portipo")	
    public ResponseEntity<List<Room>> getRoomByType(@RequestParam("type") String type) {
        return ResponseEntity.ok(roomService.getRoomsByType(type));
    }

    @GetMapping("/porquantia")
    public ResponseEntity<List<Room>> getRoomBySleep(@RequestParam("sleep") String sleep) {
        return ResponseEntity.ok(roomService.getRoomsBySleep(sleep));
    }

    @GetMapping("/status={roomStatus}")
    public ResponseEntity<List<Room>> getRoomsByStatus(@PathVariable("roomStatus") String roomStatus){
        return ResponseEntity.ok(roomService.getRoomsByStatus(roomStatus));
    }

    @PostMapping
    public ResponseEntity<Room> createGuest(@RequestBody RoomDto roomDto){
        Room room = roomService.createRoom(roomDto);
        return ResponseEntity.status(HttpStatus.OK).body(roomService.save(room));
    }
}