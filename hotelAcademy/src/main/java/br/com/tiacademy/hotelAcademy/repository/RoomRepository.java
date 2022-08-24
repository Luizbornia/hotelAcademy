package br.com.tiacademy.hotelAcademy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.tiacademy.hotelAcademy.core.crud.CrudRepository;
import br.com.tiacademy.hotelAcademy.model.Room;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {

    @Query(value = "SELECT * FROM ROOM r where r.ROOM_TYPE like %:type%", nativeQuery = true)
    List<Room> findByRoomType(@Param("type") String type);

}
