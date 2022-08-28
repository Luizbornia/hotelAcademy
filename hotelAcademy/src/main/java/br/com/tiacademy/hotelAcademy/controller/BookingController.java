package br.com.tiacademy.hotelAcademy.controller;

import br.com.tiacademy.hotelAcademy.core.crud.CrudController;
import br.com.tiacademy.hotelAcademy.dto.BookingDto;
import br.com.tiacademy.hotelAcademy.exceptions.*;
import br.com.tiacademy.hotelAcademy.model.*;
import br.com.tiacademy.hotelAcademy.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/reservas")
public class BookingController extends CrudController<Booking, Long> {

    @Autowired
    protected BookingService bookingService;

    @GetMapping("/hospede={GuestId}")
    public ResponseEntity<List<Booking>> findBookingByGuestId(@PathVariable("GuestId") Long GuestId) {
            return ResponseEntity.status(HttpStatus.OK).body(bookingService.findBookingByGuestId(GuestId));
    }
    @GetMapping("/status={bookingStatus}")
    public ResponseEntity<List<Booking>> findBookingsByReservationStatus(@PathVariable("bookingStatus") String status){
        return ResponseEntity.ok(bookingService.findBookingsByReservationStatus(status));
    }
    @PostMapping("/{roomNumber}/{mainGuestId}/{dependentId}")
    public ResponseEntity<Booking> createBooking(@PathVariable("roomNumber") Long roomNumber, @PathVariable("mainGuestId") Long mainGuest,
                                                 @PathVariable("dependentId") Long dependentId, @RequestBody BookingDto bookingDto) {
            Booking booking = bookingService.createBooking(roomNumber, mainGuest, dependentId, bookingDto);
            return ResponseEntity.status(HttpStatus.OK).body(booking);
    }
    @PutMapping("/checkout={bookingId}")
    public ResponseEntity<Booking> endReservation(@PathVariable("bookingId") Long bookingId) {
        return ResponseEntity.ok(bookingService.endReservation(bookingId));
    }
}