package br.com.tiacademy.hotelAcademy;

import br.com.tiacademy.hotelAcademy.model.RoomType;
import br.com.tiacademy.hotelAcademy.model.Sleep;

import java.util.Date;

public class CalculateReservation {

    public Integer dailyAmount(Date finalDate, Date initialDate) {
       var difference = finalDate.getTime() - initialDate.getTime();
       return (int) difference;
    }

    public double calculateReservation(RoomType roomType, Sleep sleep, Integer dailyAmount ) {
        double roomValue = 0;

        if (roomType.equals(RoomType.STANDARD) && sleep.equals(Sleep.INDIVIDUAL)) {
            roomValue = 90;
        }
        if (roomType.equals(RoomType.STANDARD) && sleep.equals(Sleep.DUPLA)) {
            roomValue = 130;
        }
        if (roomType.equals(RoomType.MASTER) && sleep.equals(Sleep.INDIVIDUAL)) {
            roomValue = 150;
        }
        if (roomType.equals(RoomType.MASTER) && sleep.equals(Sleep.DUPLA)) {
            roomValue = 200;
        }
        if (roomType.equals(RoomType.DELUXE) && sleep.equals(Sleep.INDIVIDUAL)){
            roomValue = 210;
        }
        if (roomType.equals(RoomType.DELUXE) && sleep.equals(Sleep.DUPLA)) {
            roomValue = 285;
        }

        return roomValue * dailyAmount;
    }

}
