package br.com.tiacademy.hotelAcademy.repository;

import br.com.tiacademy.hotelAcademy.core.crud.CrudRepository;
import br.com.tiacademy.hotelAcademy.model.Guest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends CrudRepository<Guest, Long> {

    @Query(value = "SELECT * FROM guest g WHERE g.CPF like :cpf",nativeQuery = true)
    Guest findGuestByCpf(@Param("cpf") String Cpf);

}