package br.com.tiacademy.hotelAcademy.exceptions;

public class RoomAlreadyReservedException extends RuntimeException {
    public RoomAlreadyReservedException(){
        super("Quarto possui reserva ativa");
    }
}
