package br.com.tiacademy.hotelAcademy.service;

import br.com.tiacademy.hotelAcademy.core.crud.CrudService;
import br.com.tiacademy.hotelAcademy.dto.GuestDto;
import br.com.tiacademy.hotelAcademy.exceptions.GuestAlreadyExistsException;
import br.com.tiacademy.hotelAcademy.exceptions.InvalidCpfLenghtException;
import br.com.tiacademy.hotelAcademy.model.Guest;
import br.com.tiacademy.hotelAcademy.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
public class GuestService extends CrudService<Guest, Long> {

    @Override
    protected Guest editName(Guest infos, Guest entity) {
        infos.setName(entity.getName());
        infos.setBirthday(entity.getBirthday());
        infos.setCpf(entity.getCpf());
        infos.setEmail(entity.getEmail());
        infos.setCity(entity.getCity());
        infos.setState(entity.getState());
        infos.setStreet(entity.getStreet());
        infos.setNumber(entity.getNumber());
        infos.setZipCode(entity.getZipCode());
        return infos;
    }

    @Autowired
    protected GuestRepository guestRepository;
    public Guest findGuestByCpf(String cpf) {
        return guestRepository.findGuestByCpf(cpf);
    }
    public String validateIfCpfExists(String cpf){
        return guestRepository.validateIfCpfExists(cpf).orElse(null);
    }

    public Guest createGuest(GuestDto guestDto) {
        Guest guest = new Guest();

        guest.setName(guestDto.getName());
        guest.setCpf(guestDto.getCpf());
        guest.setBirthday(guestDto.getBirthday());
        guest.setCity(guestDto.getCity());
        guest.setState(guestDto.getState());
        guest.setZipCode(guestDto.getZipCode());
        guest.setStreet(guestDto.getStreet());
        guest.setNumber(guestDto.getNumber());
        guest.setEmail(guestDto.getEmail());

        if (guest.getCpf().length() != 11){
            throw new InvalidCpfLenghtException();
        }

        if (Objects.isNull(validateIfCpfExists(guest.getCpf()))) {
            return guestRepository.save(guest);
        }
        else {
            throw new GuestAlreadyExistsException();
        }
    }
}
