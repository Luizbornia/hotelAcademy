package br.com.tiacademy.hotelAcademy.controller;

import br.com.tiacademy.hotelAcademy.core.crud.CrudController;
import br.com.tiacademy.hotelAcademy.model.Guest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hospedes")
public class GuestController extends CrudController<Guest, Long> {



}
