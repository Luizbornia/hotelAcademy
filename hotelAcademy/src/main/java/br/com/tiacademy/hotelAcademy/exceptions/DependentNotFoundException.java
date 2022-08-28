package br.com.tiacademy.hotelAcademy.exceptions;

public class DependentNotFoundException extends RuntimeException {
    public DependentNotFoundException(){
        super("Dependente informado não existe!");
    }
}
