package br.com.tiacademy.hotelAcademy.controller;

import br.com.tiacademy.hotelAcademy.core.crud.CrudController;
import br.com.tiacademy.hotelAcademy.model.Room;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quartos")
public class RoomController extends CrudController<Room, Long> {

    
}