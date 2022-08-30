package br.com.tiacademy.hotelAcademy.exceptions;

public class RoomHasBookingBetweenInformedDateException extends RuntimeException {
    public RoomHasBookingBetweenInformedDateException(){
        super("Quarto já agendado no periodo informado!");
    }
}
