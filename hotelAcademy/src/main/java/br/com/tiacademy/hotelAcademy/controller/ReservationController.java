package br.com.tiacademy.hotelAcademy.controller;

import br.com.tiacademy.hotelAcademy.CalculateReservation;
import br.com.tiacademy.hotelAcademy.core.crud.CrudController;
import br.com.tiacademy.hotelAcademy.dto.ReservationDto;
import br.com.tiacademy.hotelAcademy.model.Guest;
import br.com.tiacademy.hotelAcademy.model.Reservation;
import br.com.tiacademy.hotelAcademy.model.ReservationStatus;
import br.com.tiacademy.hotelAcademy.model.Room;
import br.com.tiacademy.hotelAcademy.repository.GuestRepository;
import br.com.tiacademy.hotelAcademy.repository.RoomRepository;
import br.com.tiacademy.hotelAcademy.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/reservas")
public class ReservationController extends CrudController<Reservation, Long> {

    @Autowired
    protected ReservationService reservationService;

    @Autowired
    protected CalculateReservation calculateReservation;

    @GetMapping("/hospede={MainGuestId}")
    public ResponseEntity<List<Reservation>> findReservationsByMainGuestId(@PathVariable("MainGuestId") Long id) {
            return ResponseEntity.status(HttpStatus.OK).body(reservationService.findReservationsByMainGuestId(id));
    }

    // @PostMapping("/checkin")
    // public ResponseEntity<Object> registerReservation(@RequestBody ReservationDto reservationDto) {
    //     Reservation reservation = new Reservation();

    //     reservation.setInitialDate(reservationDto.getInitialDate()); // criar metodo para pegar a data inicial no ReservationDto
    //     reservation.setFinalDate(reservationDto.getFinalDate()); //  criar metodo para pegar a data final no ReservationDto
    //     //reservation.setRoom(reservationDto.getRoom()); // validar se o quarto está ocupado ou reservado

    //     //return ResponseEntity.status(HttpStatus.CREATED).body(reservationService.registerReservation(reservation));

    //     return ResponseEntity.ok().build();
    // }


    @GetMapping("/status={reservationStatus}")
    public ResponseEntity<List<Reservation>> findReservationsByReservationStatus(@PathVariable("reservationStatus") String status){
        return ResponseEntity.ok(reservationService.findReservationsByReservationStatus(status));
    }

    @Autowired
    protected RoomRepository roomRepository;
    @Autowired
    protected GuestRepository guestRepository;

    @PostMapping("/{room_number}/{mainGuestId}")
    public ResponseEntity<Object> createReservation(@RequestBody ReservationDto reservationDto,@PathVariable("room_number") Long roomNumber,@PathVariable("mainGuestId") Long mainGuest ){
        Reservation reservation = new Reservation();

        Room room = roomRepository.findById(roomNumber).orElse(null);
        if (Objects.isNull(room)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quarto informado não existe!");
        }

        Guest guest = guestRepository.findById(mainGuest).orElse(null);
        if (Objects.isNull(guest)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hospede informado não existe!");
        }

        reservation.setInitialDate(reservation.getInitialDate());
        reservation.setFinalDate(reservation.getFinalDate());
        reservation.setRoom(room);
        reservation.setGuest(guest);
        reservation.setReservationStatus(reservationDto.getReservationStatus());
        reservation.setReservationValue(reservationDto.getReservationValue());

        return ResponseEntity.ok(reservationService.save(reservation));



        /*if (reservationService.roomIsAvailable(roomNumber).equals("ACTIVE")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quarto não está disponivel");
        }*/


        //return ResponseEntity.ok(reservationService.createReservation(roomNumber, mainGuest));
    }

}