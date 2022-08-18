package br.com.tiacademy.hotelAcademy.service;

import br.com.tiacademy.hotelAcademy.CalculateReservation;
import br.com.tiacademy.hotelAcademy.core.crud.CrudService;
import br.com.tiacademy.hotelAcademy.model.Reservation;
import br.com.tiacademy.hotelAcademy.model.ReservationStatus;
import br.com.tiacademy.hotelAcademy.model.RoomType;
import br.com.tiacademy.hotelAcademy.model.Sleep;
import br.com.tiacademy.hotelAcademy.repository.ReservationRepository;
import br.com.tiacademy.hotelAcademy.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService extends CrudService<Reservation, Long> implements CalculateReservation {

    @Override // Corrigir
    protected Reservation editName(Reservation infos, Reservation entity) {
        return null;
    }

    @Autowired
    protected ReservationRepository reservationRepository;
    @Autowired
    protected RoomRepository roomRepository;

    public Reservation registerReservation(Reservation reservation) {
        /* if (Room.isAvailable()) {
            return reservationRepository.registerReservation(reservation);
        } else {
            return reservationRepository.roomIsOcuppied();
        }
         */
        //return reservationRepository.registerReservation(reservation);
        return null;
    }

    // Pedir ajuda para Kassia

   /* public Integer dailyAmount(Date finalDate, Date initialDate) {
        var difference = finalDate.getTime() - initialDate.getTime();
        return (int) difference;
    }*/

    public double calculateReservation(RoomType roomType, Sleep sleep, Integer dailyAmount) {

        double roomValue = 0;

        if (roomType.equals(RoomType.STANDARD) && sleep.equals(Sleep.SINGLE)) {
            roomValue = 90;
        }
        if (roomType.equals(RoomType.STANDARD) && sleep.equals(Sleep.DUO)) {
            roomValue = 130;
        }
        if (roomType.equals(RoomType.MASTER) && sleep.equals(Sleep.SINGLE)) {
            roomValue = 150;
        }
        if (roomType.equals(RoomType.MASTER) && sleep.equals(Sleep.DUO)) {
            roomValue = 200;
        }
        if (roomType.equals(RoomType.DELUXE) && sleep.equals(Sleep.SINGLE)){
            roomValue = 210;
        }
        if (roomType.equals(RoomType.DELUXE) && sleep.equals(Sleep.DUO)) {
            roomValue = 285;
        }

        return roomValue * dailyAmount;
    }

    public List<Reservation> findReservationsByMainGuestId(Long id) {
        return reservationRepository.findReservationsByMainGuestId(id);
    }

    public List<Reservation> findReservationsByReservationStatus(String status){
       return reservationRepository.findReservationsByReservationStatus(status);
    }

   /* public Reservation createReservation(Long roomNumber, Long mainGuest) {


        return reservationRepository.createReservation(roomNumber, mainGuest);
    }*/

    public String roomIsAvailable(Long roomNumber) {
        return reservationRepository.roomIsAvailable(roomNumber);

    }

}
