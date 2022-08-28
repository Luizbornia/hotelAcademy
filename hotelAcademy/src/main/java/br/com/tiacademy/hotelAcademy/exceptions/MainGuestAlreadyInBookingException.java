package br.com.tiacademy.hotelAcademy.exceptions;

public class MainGuestAlreadyInBookingException extends RuntimeException{

    public MainGuestAlreadyInBookingException(){
        super("Hospede informado consta em reserva ativa!");
    }

}
