package br.com.tiacademy.hotelAcademy.repository;

import br.com.tiacademy.hotelAcademy.core.crud.CrudRepository;
import br.com.tiacademy.hotelAcademy.model.Booking;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {

    // Query para  o GetMapping
    @Query(value = "SELECT * FROM booking b WHERE b.MAIN_GUEST_ID = :mainGuestId GROUP BY b.ID", nativeQuery = true)
    List <Booking> findReservationsByMainGuestId(@Param("mainGuestId")Long GuestId);

    // Query para o GetMapping
     @Query(value = "SELECT * FROM booking b WHERE b.BOOKING_STATUS like %:status% GROUP BY b.ID", nativeQuery = true)
    List <Booking>  findReservationsByReservationStatus(@Param("status")String bookingstatus);

     // Query para o PostMapping
    @Query(value = "SELECT ROOM_NUMBER FROM booking b WHERE ROOM_NUMBER = :roomNumber AND BOOKING_STATUS like 'ACTIVE' " ,nativeQuery = true)
    Long roomIsOccupied(@Param("roomNumber") Long roomNumber);

    @Query(value = "SELECT ROOM_NUMBER FROM room r WHERE ROOM_NUMBER = :roomNumber", nativeQuery = true)
    boolean roomExists(@Param("") Long roomNumber);

}