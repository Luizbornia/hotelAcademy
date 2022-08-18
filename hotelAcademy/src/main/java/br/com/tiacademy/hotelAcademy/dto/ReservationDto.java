package br.com.tiacademy.hotelAcademy.dto;

import br.com.tiacademy.hotelAcademy.model.ReservationStatus;
import br.com.tiacademy.hotelAcademy.model.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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


    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    private Long reservationValue;
}
