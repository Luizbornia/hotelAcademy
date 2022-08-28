package br.com.tiacademy.hotelAcademy.exceptions;

public class RoomAlreadyExistsException extends RuntimeException {

    public RoomAlreadyExistsException(){
        super("Quarto informado já cadastrado");
    }
}