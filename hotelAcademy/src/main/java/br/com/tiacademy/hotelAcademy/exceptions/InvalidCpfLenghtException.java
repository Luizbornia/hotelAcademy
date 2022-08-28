package br.com.tiacademy.hotelAcademy.exceptions;

public class InvalidCpfLenghtException extends RuntimeException {

    public InvalidCpfLenghtException(){
        super("Tamanho do Cpf inválido");
    }
}