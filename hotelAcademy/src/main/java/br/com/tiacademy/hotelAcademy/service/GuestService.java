package br.com.tiacademy.hotelAcademy.service;

import br.com.tiacademy.hotelAcademy.core.crud.CrudService;
import br.com.tiacademy.hotelAcademy.model.Guest;
import org.springframework.stereotype.Service;

@Service
public class GuestService extends CrudService<Guest, Long> {

    @Override // Corrigir
    protected Guest editName(Guest infos, Guest entity) {
        infos.setName(entity.getName());
        infos.setBirthday(entity.getBirthday());
        infos.setCpf(entity.getCpf());
        infos.setResponsibility(entity.getResponsibility());
        infos.setCodResponsible(entity.getCodResponsible());
        infos.setEmail(entity.getEmail());
        infos.setCity(entity.getCity());
        infos.setState(entity.getState());
        infos.setStreet(entity.getStreet());
        infos.setNumber(entity.getNumber());
        infos.setZipCode(entity.getZipCode());
        return infos;
    }
}
