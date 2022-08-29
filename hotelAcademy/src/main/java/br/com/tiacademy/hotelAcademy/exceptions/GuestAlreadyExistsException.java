package br.com.tiacademy.hotelAcademy.exceptions;

public class GuestAlreadyExistsException extends RuntimeException {

    public GuestAlreadyExistsException(){
        super("Cpf informado já é hospede");
    }
}
