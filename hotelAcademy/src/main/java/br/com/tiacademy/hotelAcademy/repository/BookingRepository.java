package br.com.tiacademy.hotelAcademy.repository;

import br.com.tiacademy.hotelAcademy.core.crud.CrudRepository;
import br.com.tiacademy.hotelAcademy.model.Booking;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {

    @Query(value = "SELECT * FROM booking b WHERE b.MAIN_GUEST_ID = :mainGuestId GROUP BY b.ID", nativeQuery = true)
    List<Booking> findBookingByGuestId(@Param("mainGuestId")Long GuestId);

     @Query(value = "SELECT * FROM booking b WHERE b.BOOKING_STATUS like %:status% GROUP BY b.ID", nativeQuery = true)
    List<Booking>  findBookingsByBookingStatus(@Param("status")String bookingstatus);

    @Query(value = "SELECT b.MAIN_GUEST_ID FROM booking b WHERE b.MAIN_GUEST_ID = :mainGuestId and b.BOOKING_STATUS like 'ACTIVE' ", nativeQuery = true)
    Long mainGuestInActiveBooking(@Param("mainGuestId") Long mainGuestId);

    @Query(value = "SELECT b.DEPENDENT_ID FROM booking b WHERE b.DEPENDENT_ID = :dependentId and b.BOOKING_STATUS like 'ACTIVE' ", nativeQuery = true)
    Long dependentInActiveBooking(@Param("dependentId") Long dependentId);

    @Query(value = "SELECT * FROM booking b WHERE b.MAIN_GUEST_ID = :guestId or b.DEPENDENT_ID = :guestId",nativeQuery = true)
    List<Booking> findAllGuestBookings(@Param("guestId") Long guestId);
}