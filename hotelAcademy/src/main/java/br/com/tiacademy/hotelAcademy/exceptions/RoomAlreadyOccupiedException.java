package br.com.tiacademy.hotelAcademy.exceptions;

public class RoomAlreadyOccupiedException extends RuntimeException {

    public RoomAlreadyOccupiedException() {
        super("Quarto informado possui reserva ativa!");
    }
}
