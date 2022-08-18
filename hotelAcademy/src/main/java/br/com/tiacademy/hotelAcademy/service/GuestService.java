package br.com.tiacademy.hotelAcademy.service;

import br.com.tiacademy.hotelAcademy.core.crud.CrudService;
import br.com.tiacademy.hotelAcademy.model.Guest;
import br.com.tiacademy.hotelAcademy.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuestService extends CrudService<Guest, Long> {

    @Override // Corrigir
    protected Guest editName(Guest infos, Guest entity) {
        return null;
    }
}
