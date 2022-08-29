package br.com.tiacademy.hotelAcademy.service;

import java.util.List;
import java.util.Objects;
import br.com.tiacademy.hotelAcademy.dto.RoomDto;
import br.com.tiacademy.hotelAcademy.exceptions.RoomAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.tiacademy.hotelAcademy.core.crud.CrudService;
import br.com.tiacademy.hotelAcademy.model.Room;
import br.com.tiacademy.hotelAcademy.model.RoomStatus;
import br.com.tiacademy.hotelAcademy.repository.RoomRepository;

@Service
public class RoomService extends CrudService<Room, Long> {

    @Autowired
    protected RoomRepository roomRepository;

    @Override
    protected Room editName(Room infos, Room entity) {
        infos.setRoomNumber(entity.getRoomNumber());
        infos.setRoomType(entity.getRoomType());
        infos.setSleep(entity.getSleep());
        return infos;
    }

    public List<Room> getRoomsByType(String type) {
        return roomRepository.findByRoomType(type);
    }

    public List<Room> getRoomsBySleep(String sleep) {
        return roomRepository.findBySleep(sleep);
    }

    public List<Room> getRoomsByStatus(String roomStatus) {
        return roomRepository.getRoomsByStatus(roomStatus);
    }

    public Long validateIfRoomExists(Long roomNumber) {
        return roomRepository.validateIfRoomExists(roomNumber).orElse(null);
    }

    public Room createRoom(RoomDto roomDto) {
        if (Objects.isNull(validateIfRoomExists(roomDto.getRoomNumber()))){
            Room room = new Room();
            room.setRoomNumber(roomDto.getRoomNumber());
            room.setSleep(roomDto.getSleep());
            room.setRoomType(roomDto.getRoomType());
            room.setRoomStatus(RoomStatus.AVAILABLE);
            return room;
        }
        else {
            throw new RoomAlreadyExistsException();
        }
    }
}
    
    
