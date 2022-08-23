package br.com.tiacademy.hotelAcademy.repository;

import br.com.tiacademy.hotelAcademy.core.crud.CrudRepository;
import br.com.tiacademy.hotelAcademy.model.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    // Query para  o GetMapping
    @Query(value = "SELECT * FROM reservation r WHERE r.MAIN_GUEST_ID = :mainGuestId GROUP BY r.ID", nativeQuery = true)
    List <Reservation> findReservationsByMainGuestId(@Param("mainGuestId")Long GuestId);

    // Query para o GetMapping
     @Query(value = "SELECT * FROM reservation r WHERE r.RESERVATION_STATUS like %:status% GROUP BY r.ID", nativeQuery = true)
    List <Reservation>  findReservationsByReservationStatus(@Param("status")String reservationstatus);

     // Query para o PostMapping
    @Query(value = "SELECT ROOM_NUMBER FROM reservation r WHERE ROOM_NUMBER = :roomNumber AND RESERVATION_STATUS like 'ACTIVE' " ,nativeQuery = true)
    Long roomIsAvailable(@Param("roomNumber") Long roomNumber);

    @Query(value = "SELECT ROOM_NUMBER FROM room r WHERE ROOM_NUMBER = :roomNumber")
    boolean roomExists(@Param("") Long roomNumber);

}