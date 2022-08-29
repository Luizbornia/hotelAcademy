package br.com.tiacademy.hotelAcademy.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.com.tiacademy.hotelAcademy.core.crud.CrudRepository;
import br.com.tiacademy.hotelAcademy.model.Room;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {

  
    @Query(value = "SELECT * FROM ROOM r where r.ROOM_TYPE like %:type%", nativeQuery = true)
    List<Room> findByRoomType(@Param("type") String type);

    @Query(value = "SELECT * FROM ROOM r where r.SLEEP like %:sleep%", nativeQuery = true)
    List<Room> findBySleep(@Param("sleep") String sleep);

    @Query(value = "SELECT ROOM_NUMBER FROM room r WHERE ROOM_NUMBER = :roomNumber", nativeQuery = true)
    Long roomExists(@Param("roomNumber")Long roomNumber);

    @Query(value = "SELECT * FROM room r where r.ROOM_STATUS like %:roomStatus%", nativeQuery = true)
    List<Room> getRoomsByStatus(@Param("roomStatus")String roomStatus);

    @Query(value = "SELECT ROOM_NUMBER FROM room r WHERE ROOM_NUMBER = :roomNumber", nativeQuery = true)
    Optional<Long> validateIfRoomExists(@Param("roomNumber") Long roomNumber);

}
