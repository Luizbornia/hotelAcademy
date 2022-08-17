package br.com.tiacademy.hotelAcademy.repository;

import br.com.tiacademy.hotelAcademy.core.crud.CrudRepository;
import br.com.tiacademy.hotelAcademy.model.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {


   /* @Query(value = "SELECT * FROM reservation r WHERE r.nome like %:name% ", nativeQuery = true)
    Reservation findReservationByName(@Param("name") String name);

    @Query//(value = "INSERT INTO ")
    Reservation registerReservation(@Param("reservation") Reservation reservation);*/
}