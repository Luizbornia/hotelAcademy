package br.com.tiacademy.hotelAcademy.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class BookingNotFoundException extends RuntimeException {
    public BookingNotFoundException(){
        super("Reserva não encontrada!");
    }
}
