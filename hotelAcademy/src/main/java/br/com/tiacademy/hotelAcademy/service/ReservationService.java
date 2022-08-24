package br.com.tiacademy.hotelAcademy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tiacademy.hotelAcademy.core.crud.CrudService;
import br.com.tiacademy.hotelAcademy.model.Reservation;
import br.com.tiacademy.hotelAcademy.model.RoomType;
import br.com.tiacademy.hotelAcademy.model.Sleep;
import br.com.tiacademy.hotelAcademy.repository.ReservationRepository;
import br.com.tiacademy.hotelAcademy.repository.RoomRepository;

@Service
public class ReservationService extends CrudService<Reservation, Long> implements CalculateReservation {
    @Autowired
    protected ReservationRepository reservationRepository;
    @Autowired
    protected RoomRepository roomRepository;

    @Override // PUT
    protected Reservation editName(Reservation infos, Reservation entity) {
        infos.setInitialDate(entity.getInitialDate());
        infos.setFinalDate(entity.getFinalDate());
        infos.setRoom(entity.getRoom());
        infos.setGuest(entity.getGuest());
        infos.setReservationStatus(entity.getReservationStatus());
        return infos;

    }



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

    public List<Reservation> findReservationsByMainGuestId(Long GuestId) {
        return reservationRepository.findReservationsByMainGuestId(GuestId);
    }

    public List<Reservation> findReservationsByReservationStatus(String reservationstatus){
       return reservationRepository.findReservationsByReservationStatus(reservationstatus);
    }

    public Long roomIsAvailable(Long roomNumber) {
        return reservationRepository.roomIsAvailable(roomNumber);
    }

    public boolean roomExists(Long roomNumber) {
        return reservationRepository.roomExists(roomNumber);
    }

}
