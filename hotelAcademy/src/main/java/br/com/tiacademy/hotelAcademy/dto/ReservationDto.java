package br.com.tiacademy.hotelAcademy.dto;

import br.com.tiacademy.hotelAcademy.model.ReservationStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class ReservationDto {
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate initialDate;

    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate finalDate;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    private Long reservationValue;
}
