package br.com.tiacademy.hotelAcademy.exceptions;

public class RoomHasReservedAndActiveBookingException extends RuntimeException {
    public RoomHasReservedAndActiveBookingException(){
        super("Quarto informado possui reserva ativa e agendada!");
    }
}
