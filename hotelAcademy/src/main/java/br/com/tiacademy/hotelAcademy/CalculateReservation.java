package br.com.tiacademy.hotelAcademy;

import java.util.Date;

public abstract class CalculateReservation {

    public Integer dailyAmount(Date finalDate, Date initialDate) {
       var difference = finalDate.getTime() - initialDate.getTime();
       return (int) difference;
    }

    public double calculateReservation(Enum accommodationType,Enum accommodationClass, Integer dailyAmount ) {
        return 1.5;
    }

}
