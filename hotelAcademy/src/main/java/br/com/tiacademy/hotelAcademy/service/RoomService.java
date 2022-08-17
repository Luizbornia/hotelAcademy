package br.com.tiacademy.hotelAcademy.service;

import br.com.tiacademy.hotelAcademy.core.crud.CrudService;
import br.com.tiacademy.hotelAcademy.model.Room;
import org.springframework.stereotype.Service;

@Service
public class RoomService extends CrudService<Room, Long> {

    @Override // Corrigir
    protected Room editName(Room infos, Room entity) {
        return null;
    }
}
