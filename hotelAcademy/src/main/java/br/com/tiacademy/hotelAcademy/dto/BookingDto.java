package br.com.tiacademy.hotelAcademy.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class BookingDto {
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate initialDate;

    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate finalDate;
}
