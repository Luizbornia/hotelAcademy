package br.com.tiacademy.hotelAcademy.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GuestDto {
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate birthday;

    private String zipCode;
    private String name;
    private String cpf;
    private String city;
    private String state;
    private String street;
    private String number;
    private String email;

}