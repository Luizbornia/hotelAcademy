package br.com.tiacademy.hotelAcademy.service;

import java.util.List;
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

    @Override
    public Room save(Room entity) {
        entity.setRoomNumber(entity.getRoomNumber());
        entity.setSleep(entity.getSleep());
        entity.setRoomType(entity.getRoomType());
        entity.setRoomStatus(RoomStatus.AVAILABLE);
        return super.save(entity);
    }
}
    
    
