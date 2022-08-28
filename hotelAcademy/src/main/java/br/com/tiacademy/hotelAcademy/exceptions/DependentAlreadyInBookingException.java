package br.com.tiacademy.hotelAcademy.exceptions;

public class DependentAlreadyInBookingException extends RuntimeException {

    public DependentAlreadyInBookingException(){
        super("Dependente informado consta em reserva ativa!");
    }
}
