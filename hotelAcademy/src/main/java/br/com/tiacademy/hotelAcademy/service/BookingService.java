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

    public List<Booking> findAllGuestBookings(Long GuestId) {
        return bookingRepository.findAllGuestBookings(GuestId);
    }

    public Long mainGuestInActiveBooking(Long mainGuestId) {
        return bookingRepository.mainGuestInActiveBooking(mainGuestId);
    }

    public Long dependentInActiveBooking(Long dependentId) {
        return bookingRepository.dependentInActiveBooking(dependentId);
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

        if (bookingDto.getInitialDate().toEpochDay() >= today) {
            booking.setInitialDate(bookingDto.getInitialDate());
            booking.setFinalDate(bookingDto.getFinalDate());
            booking.setRoom(room);
            booking.setGuest(guest);
            booking.setBookingPrice(total);
            booking.setBookingStatus(BookingStatus.RESERVED);
        }
        else {
            booking.setInitialDate(bookingDto.getInitialDate());
            booking.setFinalDate(bookingDto.getFinalDate());
            booking.setRoom(room);
            booking.setGuest(guest);
            booking.setBookingPrice(total);
            booking.setBookingStatus(BookingStatus.ACTIVE);
            booking.getRoom().setRoomStatus(RoomStatus.OCCUPIED);
        }
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