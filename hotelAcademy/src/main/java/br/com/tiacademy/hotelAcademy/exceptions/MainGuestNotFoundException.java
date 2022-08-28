package br.com.tiacademy.hotelAcademy.exceptions;

public class MainGuestNotFoundException extends RuntimeException {

    public MainGuestNotFoundException(){
        super("Hospede informado não existe!");
    }
}
