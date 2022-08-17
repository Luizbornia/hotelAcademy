package br.com.tiacademy.hotelAcademy.repository;

import br.com.tiacademy.hotelAcademy.core.crud.CrudRepository;
import br.com.tiacademy.hotelAcademy.model.Guest;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends CrudRepository<Guest, Long> {


}
