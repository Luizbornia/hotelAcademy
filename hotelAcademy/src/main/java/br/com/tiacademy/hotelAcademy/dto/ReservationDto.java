package br.com.tiacademy.hotelAcademy.dto;

import br.com.tiacademy.hotelAcademy.model.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ReservationDto {
    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    private String initialDate;

    //@DateTimeFormat(pattern = "dd/MM/yyyy")
    private String finalDate;


     private Room room;
    // private MainGuestId mainGuestId;
}
