package br.com.tiacademy.hotelAcademy.repository;

import br.com.tiacademy.hotelAcademy.core.crud.CrudRepository;
import br.com.tiacademy.hotelAcademy.model.Booking;
import br.com.tiacademy.hotelAcademy.model.Guest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface GuestRepository extends CrudRepository<Guest, Long> {

    @Query(value = "SELECT * FROM guest g WHERE g.CPF like :cpf",nativeQuery = true)
    Guest findGuestByCpf(@Param("cpf") String Cpf);


    @Query(value = "SELECT * FROM booking b WHERE b.MAIN_GUEST_ID = :hospedeid or b.DEPENDENT_ID = :hospedeid",nativeQuery = true)
    List<Booking> findAllGuestBookings(@Param("hospedeid") Long hospedeId);
}