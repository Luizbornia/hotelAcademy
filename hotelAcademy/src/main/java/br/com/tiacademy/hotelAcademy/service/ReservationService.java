package br.com.tiacademy.hotelAcademy.service;

import br.com.tiacademy.hotelAcademy.core.crud.CrudService;
import br.com.tiacademy.hotelAcademy.entity.Reservation;
import br.com.tiacademy.hotelAcademy.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService extends CrudService<Reservation, Long> {
    @Override
    protected Reservation editName(Reservation infos, Reservation entity) {
        infos.setInitialDate(entity.getInitialDate());
        infos.setFinalDate(entity.getFinalDate());
        return infos;
    }

    @Autowired
    protected ReservationRepository reservationRepository;

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



}
