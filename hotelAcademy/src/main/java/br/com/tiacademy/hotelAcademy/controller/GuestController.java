package br.com.tiacademy.hotelAcademy.controller;

import br.com.tiacademy.hotelAcademy.core.crud.CrudController;
import br.com.tiacademy.hotelAcademy.model.Guest;
import br.com.tiacademy.hotelAcademy.repository.GuestRepository;
import br.com.tiacademy.hotelAcademy.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/hospedes")
public class GuestController extends CrudController<Guest, Long> {

}
