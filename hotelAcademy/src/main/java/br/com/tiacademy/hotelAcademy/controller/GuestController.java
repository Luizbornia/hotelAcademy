package br.com.tiacademy.hotelAcademy.controller;

import br.com.tiacademy.hotelAcademy.core.crud.CrudController;
import br.com.tiacademy.hotelAcademy.model.Booking;
import br.com.tiacademy.hotelAcademy.model.Guest;
import br.com.tiacademy.hotelAcademy.service.BookingService;
import br.com.tiacademy.hotelAcademy.service.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/hospedes")
public class GuestController extends CrudController<Guest, Long> {

    @Autowired
    protected GuestService guestService;

    @Autowired
    protected BookingService bookingService;

    @GetMapping("/cpf={cpf}")
    public ResponseEntity<Guest> findGuestByCpf(@PathVariable("cpf") String cpf) {
        return ResponseEntity.ok(guestService.findGuestByCpf(cpf));
    }

    @GetMapping("/reservasfinalizadas/{guestId}")
    public ResponseEntity<List<Booking>> findAllGuestBookings(@PathVariable("guestId") Long guestId){
        return ResponseEntity.status(HttpStatus.OK).body(bookingService.findAllGuestBookings(guestId));
    }



}
