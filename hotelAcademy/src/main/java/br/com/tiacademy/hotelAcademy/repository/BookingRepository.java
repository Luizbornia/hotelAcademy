package br.com.tiacademy.hotelAcademy.repository;

import br.com.tiacademy.hotelAcademy.core.crud.CrudRepository;
import br.com.tiacademy.hotelAcademy.model.Booking;
import br.com.tiacademy.hotelAcademy.model.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {

    @Query(value = "SELECT * FROM booking b WHERE b.MAIN_GUEST_ID = :mainGuestId GROUP BY b.ID", nativeQuery = true)
    List<Booking> findBookingByGuestId(@Param("mainGuestId")Long GuestId);

    @Query(value = "SELECT * FROM booking b WHERE b.BOOKING_STATUS like %:status% GROUP BY b.ID", nativeQuery = true)
    List<Booking>  findBookingsByBookingStatus(@Param("status")String bookingstatus);

    @Query(value = " SELECT * FROM booking b WHERE b.MAIN_GUEST_ID = :guestId or b.DEPENDENT_ID = :guestId",nativeQuery = true)
    List<Booking> findAllGuestBookings(@Param("guestId") Long guestId);

    @Query(value = "SELECT b.MAIN_GUEST_ID FROM booking b WHERE b.MAIN_GUEST_ID = :mainGuestId and b.BOOKING_STATUS like 'ACTIVE' ", nativeQuery = true)
    Long mainGuestInActiveBooking(@Param("mainGuestId") Long mainGuestId);

    @Query(value = "SELECT b.DEPENDENT_ID FROM booking b WHERE b.DEPENDENT_ID = :dependentId and b.BOOKING_STATUS like 'ACTIVE' ", nativeQuery = true)
    Long dependentInActiveBooking(@Param("dependentId") Long dependentId);

    @Query(value = "SELECT b.ROOM_NUMBER FROM booking b WHERE ROOM_NUMBER = :roomNumber and b.BOOKING_STATUS like 'RESERVED'", nativeQuery = true)
    Optional<Long> validateIfRoomIsReserved(@Param("roomNumber") Long roomNumber);

    @Query(value = "SELECT b.ROOM_NUMBER FROM booking b WHERE ROOM_NUMBER = :roomNumber and b.BOOKING_STATUS like 'ACTIVE'", nativeQuery = true)
    Optional<Long> validateIfRoomIsActive(@Param("roomNumber") Long roomNumber);

    @Query(value = "SELECT b.INITIAL_DATE FROM booking b WHERE b.ROOM_NUMBER = :roomNumber and b.BOOKING_STATUS like 'RESERVED'", nativeQuery = true)
    Optional<LocalDate> findReservedBookingInitialDate(@Param("roomNumber") Long roomNumber);

    @Query(value = "SELECT b.FINAL_DATE FROM booking b WHERE b.ROOM_NUMBER = :roomNumber and b.BOOKING_STATUS like 'Active'", nativeQuery = true)
    Optional<LocalDate> findActiveBookingFinalDate(@Param("roomNumber") Long roomNumber);

}