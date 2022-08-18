package br.com.tiacademy.hotelAcademy.repository;

import br.com.tiacademy.hotelAcademy.core.crud.CrudRepository;
import br.com.tiacademy.hotelAcademy.model.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    @Query(value = "SELECT * FROM reservation r WHERE r.MAIN_GUEST_ID = :mainGuestId GROUP BY r.ID", nativeQuery = true)
    List <Reservation> findReservationsByMainGuestId(@Param("mainGuestId")Long id);

    @Query(value = "SELECT * FROM reservation r WHERE r.RESERVATION_STATUS like %:status% GROUP BY r.ID", nativeQuery = true)
    List <Reservation>  findReservationsByReservationStatus(@Param("status")String status);

    @Query(value = "SELECT RESERVATION_STATUS FROM reservation r WHERE ROOM_NUMBER = :roomnumber", nativeQuery = true)
    String roomIsAvailable(@Param("roomnumber") Long roomNumber);

}