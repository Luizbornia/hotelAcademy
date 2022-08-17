package br.com.tiacademy.hotelAcademy.repository;

import br.com.tiacademy.hotelAcademy.core.crud.CrudRepository;
import br.com.tiacademy.hotelAcademy.model.Room;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {



}
