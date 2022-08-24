package br.com.tiacademy.hotelAcademy.controller;

import br.com.tiacademy.hotelAcademy.core.crud.CrudController;
import br.com.tiacademy.hotelAcademy.dto.BookingDto;
import br.com.tiacademy.hotelAcademy.model.*;
import br.com.tiacademy.hotelAcademy.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/reservas")
public class BookingController extends CrudController<Booking, Long> {

    @Autowired
    protected BookingService bookingService;

    @GetMapping("/hospede={MainGuestId}")
    public ResponseEntity<List<Booking>> findReservationsByMainGuestId(@PathVariable("MainGuestId") Long id) {
            return ResponseEntity.status(HttpStatus.OK).body(bookingService.findReservationsByMainGuestId(id));
    }

    @GetMapping("/status={bookingStatus}")
    public ResponseEntity<List<Booking>> findReservationsByReservationStatus(@PathVariable("bookingStatus") String status){
        return ResponseEntity.ok(bookingService.findReservationsByReservationStatus(status));
    }
    @PostMapping("/{roomNumber}/{mainGuestId}/{dependentId}")
    public ResponseEntity<Object> createReservation(@PathVariable("roomNumber") Long roomNumber, @PathVariable("mainGuestId") Long mainGuest,
                                                    @PathVariable("dependentId") Long dependentId, @RequestBody BookingDto bookingDto) {

        Booking booking = bookingService.createReservation();

        Room room = bookingService.createRoom(roomNumber);
        if (Objects.isNull(room)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quarto informado não existe");
        }

        Guest guest = bookingService.createGuest(mainGuest);
        if (Objects.isNull(guest)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Main informado não existe!");
        }

        if (room.getSleep().equals(Sleep.DUO)) {
            Guest dependent = bookingService.createGuest(dependentId);
        }
        else {
            booking.setDependentId(null);
        }

        double total = bookingService.calculateroomValue(room.getRoomType(), room.getSleep()) * bookingService.dailyAmount(bookingDto);

        booking.setInitialDate(bookingDto.getInitialDate());
        booking.setFinalDate(bookingDto.getFinalDate());
        booking.setRoom(room);
        booking.setGuest(guest);
        booking.setBookingStatus(BookingStatus.ACTIVE);
        booking.setBookingPrice(total);

        return ResponseEntity.status(HttpStatus.OK).body(bookingService.save(booking));
    }

    @PutMapping("/checkout={bookingId}")
    public ResponseEntity<Object> endReservation(@PathVariable("bookingId") Long bookingId) {
        Booking booking = bookingService.checkout(bookingId);
        if (Objects.isNull(booking)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reserva informada não existe");
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(booking);
        }
    }
}