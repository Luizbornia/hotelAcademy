package br.com.tiacademy.hotelAcademy.service;

import br.com.tiacademy.hotelAcademy.core.crud.CrudService;
import br.com.tiacademy.hotelAcademy.dto.BookingDto;
import br.com.tiacademy.hotelAcademy.exceptions.*;
import br.com.tiacademy.hotelAcademy.model.*;
import br.com.tiacademy.hotelAcademy.repository.GuestRepository;
import br.com.tiacademy.hotelAcademy.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
@Service
public class BookingService extends CrudService<Booking, Long> {
    @Autowired
    protected BookingRepository bookingRepository;
    @Autowired
    protected RoomService roomService;
    @Autowired
    protected GuestRepository guestRepository;

    @Override // PUT
    protected Booking editName(Booking infos, Booking entity) {
        infos.setInitialDate(entity.getInitialDate());
        infos.setFinalDate(entity.getFinalDate());
        infos.setRoom(entity.getRoom());
        infos.setGuest(entity.getGuest());
        infos.setBookingStatus(entity.getBookingStatus());
        return infos;
    }

    public double calculateRoomValue(RoomType roomType, Sleep sleep, BookingDto dto) {
        long finalDate = dto.getFinalDate().toEpochDay();
        long initialDate = dto.getInitialDate().toEpochDay();
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
        if (roomType.equals(RoomType.DELUXE) && sleep.equals(Sleep.SINGLE)) {
            roomPrice = 210;
        }
        if (roomType.equals(RoomType.DELUXE) && sleep.equals(Sleep.DUO)) {
            roomPrice = 285;
        }
        return roomPrice * (finalDate - initialDate);
    }

    public List<Booking> findBookingByGuestId(Long GuestId) {
        return bookingRepository.findBookingByGuestId(GuestId);
    }

    public List<Booking> findBookingsByBookingStatus(String bookingstatus) {
        return bookingRepository.findBookingsByBookingStatus(bookingstatus);
    }

    public List<Booking> findAllGuestBookings(Long guestId) {
        return bookingRepository.findAllGuestBookings(guestId);
    }

    public Long mainGuestInActiveBooking(Long mainGuestId) {
        return bookingRepository.mainGuestInActiveBooking(mainGuestId);
    }

    public Long dependentInActiveBooking(Long dependentId) {
        return bookingRepository.dependentInActiveBooking(dependentId);
    }

    public long findReservedBookingInitialDate(Long roomNumber){
        LocalDate reservedBookingInitialDate = bookingRepository.findReservedBookingInitialDate(roomNumber).orElse(null);
        if (Objects.isNull(reservedBookingInitialDate)){
            return -1;
        }
        else {
            return reservedBookingInitialDate.toEpochDay();
        }
    }
    public long findActiveBookingFinalDate(Long roomNumber){
        LocalDate activeBookingFinalDate = bookingRepository.findActiveBookingFinalDate(roomNumber).orElse(null);
        if (Objects.isNull(activeBookingFinalDate)){
            return -1;
        }
        else {
            return activeBookingFinalDate.toEpochDay();
        }
    }

    public Long validateIfRoomIsReserved(Long roomNumber){
        return bookingRepository.validateIfRoomIsReserved(roomNumber).orElse(null);
    }

    public Long validateIfRoomIsActive(Long roomNumber){
        return bookingRepository.validateIfRoomIsActive(roomNumber).orElse(null);
    }

    public Booking createBooking(Long roomNumber, Long mainGuest, Long dependentId, BookingDto bookingDto) {
        Booking booking = new Booking();

        Room room = roomService.findById(roomNumber);
        if (Objects.isNull(room)) {
            throw new RoomNotFoundException();
        }

        if (room.getRoomStatus().equals(RoomStatus.OCCUPIED)) {
            throw new RoomAlreadyOccupiedException();
        }

        Guest guest;
        if (room.getSleep().equals(Sleep.DUO)) {
            guest = guestRepository.findById(mainGuest).orElse(null);
            if (Objects.isNull(guest)) {
                throw new MainGuestNotFoundException();
            }
            if (guest.getId().equals(mainGuestInActiveBooking(mainGuest)) || guest.getId().equals(dependentInActiveBooking(mainGuest))) {
                throw new MainGuestAlreadyInBookingException();
            }
            Guest dependent = guestRepository.findById(dependentId).orElse(null);
            if (Objects.isNull(dependent)) {
                throw new DependentNotFoundException();
            }
            if (dependent.getId().equals(mainGuestInActiveBooking(dependentId)) || dependent.getId().equals(dependentInActiveBooking(dependentId))){
                throw new DependentAlreadyInBookingException();
            }
            if (guest.getId().equals(dependent.getId())) {
                throw new MainGuestAndDependentAreTheSameException();
            }
            booking.setDependent(dependent);
        }
        else {
            guest = guestRepository.findById(mainGuest).orElse(null);
            if (Objects.isNull(guest)) {
                throw new MainGuestNotFoundException();
            }
            if (guest.getId().equals(mainGuestInActiveBooking(mainGuest)) || guest.getId().equals(dependentInActiveBooking(mainGuest))) {
                throw new MainGuestAlreadyInBookingException();
            }
            booking.setDependent(null);
        }
        setBookingConfigurations(booking, bookingDto, room, guest);
        return bookingRepository.save(booking);
    }

    private void setBookingConfigurations(Booking booking, BookingDto bookingDto, Room room, Guest guest) {
        double total = calculateRoomValue(room.getRoomType(), room.getSleep(), bookingDto);

        long today = LocalDate.now().toEpochDay();
        long roomNumber = room.getRoomNumber();
        long dtoInitialDate = bookingDto.getInitialDate().toEpochDay();
        long dtoFinalDate = bookingDto.getFinalDate().toEpochDay();
        long reservedBookingInitialDate = findReservedBookingInitialDate(roomNumber);
        long activeBookingFinalDate = findActiveBookingFinalDate(roomNumber);

        // Se nenhuma reserva ativa ou agendada do quarto informado no banco de dados, criar dependendo do dtoInitialDate
        if (validateIfRoomIsActive(roomNumber) == null && validateIfRoomIsReserved(roomNumber) == null) {
            if (dtoInitialDate == today) {
                booking.setBookingStatus(BookingStatus.ACTIVE);
            }
            else if (dtoInitialDate > today){
                booking.setBookingStatus(BookingStatus.RESERVED);
            }
        }
        // Se existir uma reserva agendada e não existir uma ativa e se o dtoFinalDate <= a dataInitial da reserva agendada, criar uma ativa
        // se dtoInitialDate entre as datas da reserva agendada, exceção RoomHasBookingBetweenInformedDateException();
        else if (validateIfRoomIsReserved(roomNumber) != null && validateIfRoomIsActive(roomNumber) == null) { // OK
            if (dtoFinalDate <= reservedBookingInitialDate){
                booking.setBookingStatus(BookingStatus.ACTIVE);
            }
            else {
                throw new RoomHasBookingBetweenInformedDateException();
            }
        }
        // Se existir uma reserva ativa e não existir uma agendada e se o dtoInitialDate >= dataFinal da reserva ativa, criar agendada
        // para criar uma reserva agendada no mesmo quarto, hospedes devem ser diferentes.
        else if (validateIfRoomIsActive(roomNumber) != null && validateIfRoomIsReserved(roomNumber) == null) {
            if (dtoInitialDate >= activeBookingFinalDate) {
                booking.setBookingStatus(BookingStatus.RESERVED);
            }
            else {
                throw new RoomAlreadyOccupiedException();
            }
        }
        // Se existir reserva agendada e ativa, exceção RoomHasReservedAndActiveBookingException()
        else {
            throw new RoomHasReservedAndActiveBookingException();
        }

        booking.setRoom(room);
        booking.setInitialDate(bookingDto.getInitialDate());
        booking.setFinalDate(bookingDto.getFinalDate());
        booking.setGuest(guest);
        booking.setBookingPrice(total);
    }

    public Booking endReservation(Long bookingId) {
        Booking booking = checkout(bookingId);
        if (Objects.isNull(booking)) {
            throw new BookingNotFoundException();
        }
        else {
            return booking;
        }
    }

    public Booking checkout(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElse(null);
        if (Objects.isNull(booking)) {
            return null;
        }
        else {
            booking.setBookingStatus(BookingStatus.CONCLUDED);
            booking.getRoom().setRoomStatus(RoomStatus.AVAILABLE);
            return bookingRepository.save(booking);
        }
    }
}