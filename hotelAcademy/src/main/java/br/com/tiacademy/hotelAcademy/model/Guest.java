package br.com.tiacademy.hotelAcademy.model;

import java.time.LocalDate;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate birthday;

    @Column(name = "ZIP_CODE")
    private String zipCode;
    private String name;
    private String cpf;
    private String city;
    private String state;
    private String street;
    private String number;
    private String email;

}