package br.com.tiacademy.hotelAcademy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.*;
import java.util.Date;

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

    private Long codResponsible;
    private String name;
    private String cpf;
    private Date birthday;
    private String city;
    private String state;
    private String cep;
    private String street;
    private String number;
    private String email;

}
