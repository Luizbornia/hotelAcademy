package br.com.tiacademy.hotelAcademy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ReservationDto {
    private String initialDate;
    private String finalDate;

    Date today = new Date();

    // private Room room;
    // private MainGuestId mainGuestId;
}
