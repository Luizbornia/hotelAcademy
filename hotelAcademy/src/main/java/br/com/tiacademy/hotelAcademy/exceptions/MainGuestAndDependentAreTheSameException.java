package br.com.tiacademy.hotelAcademy.exceptions;

public class MainGuestAndDependentAreTheSameException extends RuntimeException {

    public MainGuestAndDependentAreTheSameException(){
        super("Hospede informado e Dependente são iguais!");
    }

}
