package br.com.tiacademy.hotelAcademy.exceptions;

public class RoomNotFoundException extends RuntimeException {

    public RoomNotFoundException() {
        super("Quarto informado não existe!");
    }
}
