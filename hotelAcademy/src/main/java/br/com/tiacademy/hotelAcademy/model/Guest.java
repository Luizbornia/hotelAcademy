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

    @Enumerated(EnumType.STRING)
    private Responsibility responsibility;

    /*@ManyToOne*/
    private Long responsibleId; // Guest
    private String name;
    private String cpf;

    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate birthday;
    private String city;
    private String state;

    @Column(name = "cep")
    private String zipCode;
    private String street;
    private String number;
    private String email;

}