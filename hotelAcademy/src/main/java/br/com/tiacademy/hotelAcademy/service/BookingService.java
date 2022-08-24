package br.com.tiacademy.hotelAcademy.service;

import br.com.tiacademy.hotelAcademy.core.crud.CrudService;
import br.com.tiacademy.hotelAcademy.dto.BookingDto;
import br.com.tiacademy.hotelAcademy.model.*;
import br.com.tiacademy.hotelAcademy.repository.GuestRepository;
import br.com.tiacademy.hotelAcademy.repository.BookingRepository;
import br.com.tiacademy.hotelAcademy.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class BookingService extends CrudService<Booking, Long> {
    @Autowired
    protected BookingRepository reservationRepository;
    @Autowired
    protected RoomRepository roomRepository;
    @Autowired
    protected GuestRepository guestRepository;

    @Override // PUT
    protected Booking editName(Booking infos, Booking entity) {
        infos.setInitialDate(entity.getInitialDate());
        infos.setFinalDate(entity.getFinalDate());
        infos.setRoom(entity.getRoom());
        infos.setGuest(entity.getGuest());
        infos.setReservationStatus(entity.getReservationStatus());
        return infos;

    }

    public Booking createReservation(){
        return new Booking();
    }

    public Room createRoom(Long roomNumber) {
        return roomRepository.findById(roomNumber).orElse(null);
    }

    public Guest createGuest(Long mainGuest){
        return guestRepository.findById(mainGuest).orElse(null);
    }

    public Long dailyAmount(BookingDto dto) {

        LocalDate finalDate = dto.getFinalDate();
        LocalDate initialDate = dto.getInitialDate();

        return finalDate.toEpochDay() - initialDate.toEpochDay();
    }

   /* public Long getTodayDate(ReservationDto dto){
        long initialDate = dto.getInitialDate().toEpochDay();
        long today = LocalDate.now().toEpochDay();
        if (initialDate > today) {
            return today;
        }
    }*/
    public double calculateroomValue(RoomType roomType, Sleep sleep) {

        double roomPrice = 0;

        if (roomType.equals(RoomType.STANDARD) && sleep.equals(Sleep.SINGLE)) {
            roomPrice = 90;
        }
        if (roomType.equals(RoomType.STANDARD) && sleep.equals(Sleep.DUO)) {
            roomPrice = 130;
        }
        if (roomType.equals(RoomType.MASTER) && sleep.equals(Sleep.SINGLE)) {
            roomPrice = 150;
        }
        if (roomType.equals(RoomType.MASTER) && sleep.equals(Sleep.DUO)) {
            roomPrice = 200;
        }
        if (roomType.equals(RoomType.DELUXE) && sleep.equals(Sleep.SINGLE)){
            roomPrice = 210;
        }
        if (roomType.equals(RoomType.DELUXE) && sleep.equals(Sleep.DUO)) {
            roomPrice = 285;
        }

        return roomPrice;
    }

    public List<Booking> findReservationsByMainGuestId(Long GuestId) {
        return reservationRepository.findReservationsByMainGuestId(GuestId);
    }

    public List<Booking> findReservationsByReservationStatus(String reservationstatus){
       return reservationRepository.findReservationsByReservationStatus(reservationstatus);
    }

    public Long roomIsAvailable(Long roomNumber) {
        return reservationRepository.roomIsAvailable(roomNumber);
    }

    public boolean roomExists(Long roomNumber) {
        return reservationRepository.roomExists(roomNumber);
    }

    public Booking checkout(Long reservationId) {
        Booking reservation = reservationRepository.findById(reservationId).orElse(null);
        if (Objects.isNull(reservation)) {
            return null;
        }
        else {
            reservation.setReservationStatus(BookingStatus.CONCLUDED);
            return reservationRepository.save(reservation);
        }
    }


}
