package br.com.tiacademy.hotelAcademy.controller;

import br.com.tiacademy.hotelAcademy.CalculateReservation;
import br.com.tiacademy.hotelAcademy.core.crud.CrudController;
import br.com.tiacademy.hotelAcademy.dto.ReservationDto;
import br.com.tiacademy.hotelAcademy.model.Reservation;
import br.com.tiacademy.hotelAcademy.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservas")
public class ReservationController extends CrudController<Reservation, Long> {

    @Autowired
    protected ReservationService reservationService;

    @Autowired
    protected CalculateReservation calculateReservation;

    // @PostMapping("/checkin")
    // public ResponseEntity<Object> registerReservation(@RequestBody ReservationDto reservationDto) {
    //     Reservation reservation = new Reservation();

    //     reservation.setInitialDate(reservationDto.getInitialDate()); // criar metodo para pegar a data inicial no ReservationDto
    //     reservation.setFinalDate(reservationDto.getFinalDate()); //  criar metodo para pegar a data final no ReservationDto
    //     //reservation.setRoom(reservationDto.getRoom()); // validar se o quarto está ocupado ou reservado

    //     //return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.registerReservation(reservation));

    //     return ResponseEntity.ok().build();
    // }


}
